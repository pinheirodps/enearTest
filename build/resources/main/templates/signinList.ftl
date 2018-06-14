<#--<#ftl strip_whitespace=true>-->
<html xmlns:th="http://www.thymeleaf.org">

<#import "spring.ftl" as spring />
<#--<#assign form=JspTaglibs["http://www.springframework.org/tags/form"] />-->
<#--<@spring.bind "webPage" />-->


<html>
<head>
    <title>List Customer</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">

    </script>

    <script>
        $(document).ready(function() {
            console.log("ssssssss");

            (function( $ ){
                $("#btnJson").click(function(){
                    searchAjax();
                });
            })(jQuery);

            var dataGlobal = {}


        var  searchAjax = function () {
            var surname, postCode;
            surname =  document.getElementById("surname").value;
            postCode =  document.getElementById("postCode").value;

            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "/signinFilterListJson",
                data : JSON.stringify({ 'surname': surname, "postCode":postCode }),
                dataType : 'json',
                timeout : 100000,
                success : function(data) {
                    var arr=[<#list messages.thread.messages.topic as message>${message.body},</#list>]
                    console.log("SUCCESS: ", data);
                    console.log(arr);

                    dataGlobal = data;
                    display(data);
                },
                error : function(e) {
                    console.log("ERROR: ", e);
                },
                done : function(e) {
                    console.log("DONE");
                }
            });
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

        .datatable {margin-bottom:5px;border:1px solid #eee;border-collapse:collapse;width:400px;max-width:100%;font-family:Calibri}
        .datatable th {padding:3px;border:1px solid #888;height:30px;background-color:#B2D487;text-align:center;vertical-align:middle;color:#444444}
        .datatable tr {border:1px solid #888}
        .datatable tr.odd {background-color:#eee}
        .datatable td {padding:2px;border:1px solid #888}
        #content { padding 5px; margin: 5px; text-align: center}
        fieldset { width: 300px; padding: 5px; margin-bottom: 0px; }
        legend { font-weight: bold; }
        .error {color: #ff0000;font-weight: bold; list-style-type: none;}
    </style>

</head>
<body>
<div id="header">
    <h2>List Customer</h2>
</div>
<div id="content">
    <fieldset>
        <legend>List Custormers</legend>
    <@spring.bind "customer"/>
        <form name="customer" action="/signinFilterList" method="post"  th:object="${customer}" >
            <@spring.bind "customer" />
            Surname : <input type="text" name="surname" th:field="*{surname}" /><br/><br/>
            PostCode: <input type="text" name="postCode"  /><br/><br/>
            <input type="submit" message="Filter" />

         <#if errorsMsg??>
            <ul>
               <#list errorsMsg as error>
                   <li class="error">${error.defaultMessage}</li>
               </#list>
            </ul>
         </#if>

        </form>

        <legend>List Custormers Json</legend>
    <@spring.bind "customer"/>
            <@spring.bind "customer" />
            Surname : <input type="text" name="surname" id="surname"  /><br/><br/>
            PostCode: <input type="text" name="postCode" id="postCode"  /><br/><br/>
            <input type="submit" id="btnJson" message="Filter"  />

         <#if errorsMsg??>
            <ul>
               <#list errorsMsg as error>
                   <li class="error">${error.defaultMessage}</li>
               </#list>
            </ul>
         </#if>


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
    <#if custormes??: $("${dataGlobal}")>

    <#list custormes as customer>

      <tr>
          <td>${customer.surname}</td>
          <td>${customer.forename}</td>
          <td>${customer.emailAddress}</td>
          <td> ${customer.customerType.message}
          </td>
          <td>${customer.postCode}</td>
      </tr>
        </#list>

    </#if>
    </table>

<#--<#list custormes as message>-->
<#--${(message.message)! "nada"}-->
<#--</#list>-->
</div>
</body>
</html>