<html>
<head><title>List Customer</title>

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
    </style>

<body>
<div id="header">
    <h2>List Customer</h2>
</div>
<div id="content">
    <fieldset>
        <legend>Add Car</legend>
        <form name="customer" action="/signinFilterList" method="post" modelAttribute="customer">
            Surname : <input type="text" name="surname" /><br/>
            PostCode: <input type="text" name="postCode" /><br/>
            <input type="submit" value="Save" />
        </form>
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
    <#if custormes??>

    <#list custormes as customer>

      <tr>
          <td>${customer.surname}</td>
          <td>${customer.forename}</td>
          <td>${customer.emailAddress}</td>
          <td> ${(customer.customerType.value())! ""}</td>
          <td>${customer.postCode}</td>
      </tr>
        </#list>

    </#if>
    </table>

<#list custormes as value>
${(value.value)! "nada"}
</#list>
</div>
</body>
</html>