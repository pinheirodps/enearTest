<html>
<head>
    <title>List Customer</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">

    </script>

    <script>
        $(document).ready(function () {
            $("#output").hide();
            $(".errorJson").hide();
          //  $("#result").hide();
            var showMsg = $(this).find('td').length > 0;
            if(showMsg){
                $("#result").hide();
            }else{
                $("#result").show();
                $("#result").append("no results");
            }

            (function ($) {
                $("#btnJson").click(function () {
                    searchAjax();
                });
                $("#btnList").click(function () {
                    var showMsg = $(this).find('td').length > 0;
                    if(showMsg){
                        $("#result").hide();
                    }else{
                        $("#result").css("display", "block");
                    }
                });
            })(jQuery);


            var searchAjax = function () {
                var surname, postCode;
                surname = document.getElementById("surname").value;
                postCode = document.getElementById("postCode").value;
                $("#output").empty();
                $("#result").empty();
                if (Boolean(surname) && Boolean(postCode)) {
                    $.ajax({
                        type: "POST",
                        contentType: "application/json",
                        url: "/signinFilterListJson",
                        data: JSON.stringify({'surname': surname, "postCode": postCode}),
                        dataType: 'json',
                        timeout: 100000,
                        success: function (data) {
                            console.log("SUCCESS: ", data);
                            if(data.length > 0){
                                $("#output").show();
                                $(".errorJson").hide();
                                $("#result").hide();
                                $.each(data, function (key, value) {
                                    $("#output").append('<td>' + value['forename'] + '</td>' + '<td>' + value['surname'] + '</td>'
                                            + '<td>' + value['emailAddress'] + '</td>' + '<td>' + value['customerType'].toLowerCase() + '</td>'
                                            + '<td>' + value['postCode'] + '</td>');
                                });
                            }else {
                                $("#output").empty();
                                $("#result").show();
                                $("#result").append("no results");

                            }


                        },
                        error: function (e) {
                            console.log("ERROR: ", e);
                        },
                        done: function (e) {
                            console.log("DONE");
                        }
                    });
                } else {
                    $(".errorJson").show();
                }
            }

        });
    </script>

    <style>
        body, input {
            font-family: Calibri, Arial;
            margin: 0px;
            padding: 0px;
        }

        #header h2 {
            color: white;
            background-color: #3275A8;
            height: 50px;
            padding: 5px 0 0 5px;
            font-size: 20px;
        }

        .datatable {
            margin-bottom: 5px;
            border: 1px solid #eee;
            border-collapse: collapse;
            max-width: 100%;
            font-family: Calibri
        }

        .datatable th {
            padding: 3px;
            border: 1px solid #888;
            height: 30px;
            background-color: #B2D487;
            text-align: center;
            vertical-align: middle;
            color: #444444
        }

        .datatable tr {
            border: 1px solid #888
        }

        .datatable tr.odd {
            background-color: #eee
        }

        .datatable td {
            padding: 2px;
            border: 1px solid #888
        }

        #content {
            padding 5px;
            margin: 5px;
            text-align: center
        }

        fieldset {
            width: 300px;
            padding: 5px;
            margin-bottom: 0px;
        }

        legend {
            font-weight: bold;
        }

        .error {
            color: #ff0000;
            font-weight: bold;
            list-style-type: none;
        }

        .errorJson {
            color: #ff0000;
            font-weight: bold;
            list-style-type: none;
        }
        #result{
            float: left;
        }
    </style>

</head>
<body>
<div id="header">
    <h2>Welcome ${(customer.surname)!""}</h2>
</div>
<div id="content">
    <fieldset>
        <legend>List Custormers</legend>
    <form name="customer" action="/signinFilterList" method="post">
            Surname : <input type="text" name="surname"/><br/><br/>
            PostCode: <input type="text" name="postCode"/><br/><br/>
            <input type="submit" id="btnList" message="Filter"/>

<#if errorsMsg??>
<ul>
   <#list errorsMsg as error>
       <li class="error">${error.defaultMessage}</li>
   </#list>
</ul>
</#if>
  </form>

        <legend>List Custormers Json</legend>
        Surname : <input type="text" name="surname" id="surname"/><br/><br/>
        PostCode: <input type="text" name="postCode" id="postCode"/><br/><br/>
        <input type="submit" id="btnJson" message="Filter"/>

        <ul>
            <li class="errorJson">Surname is required</li>
            <li class="errorJson">PostCode is required</li>
        </ul>


    </fieldset>
    <br/>

    <table class="datatable">
        <tr>
            <th>Surname</th>
            <th>Forename</th>
            <th>Email</th>
            <th>Type</th>
            <th>PostCode</th>
        </tr>
        <tr id="output">
        </tr>
<#if custormes??>

    <#list custormes as customer>

<tr>
    <td>${customer.surname}</td>
    <td>${customer.forename}</td>
    <td>${customer.emailAddress}</td>
    <td> ${(customer.customerType.message)!""}
    </td>
    <td>${customer.postCode}</td>
</tr>
    </#list>
<#--<#else>-->
<#--<label> sdas</label>-->

</#if>

    </table>
    <label id="result"></label>
    <#--<label id="resultList">teste</label>-->


</div>
</body>
</html>