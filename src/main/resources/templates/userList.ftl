<#import "parts/common.ftl" as c>

<@c.page>
<h4>List of users</h4>

<table class="table">
    <thead>
    <tr>
        <th scope="row"></th>
        <th scope="col">Name</th>
        <th scope="col">Role</th>
        <th scope="col">Operation</th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
        <tr>
            <th scope="row"></th>
            <td scope="col">${user.username}</td>
            <td scope="col"><#list user.roles as role> ${role}<#sep>,  </#list></td>
            <td scope="col"><a href="/user/${user.id}" class="ml-3 badge badge-success">edit</a></td>
        </tr>
    </#list>
    </tbody>
</table>
</@c.page>
