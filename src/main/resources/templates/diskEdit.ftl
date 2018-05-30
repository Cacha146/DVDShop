<#import "parts/common.ftl" as c>

<@c.page>
Disk editor

<form action="/main/update" method="post">
    <input type="text" name="name" value="${disk.name}">
    <input type="text" name="description" value="${disk.description}">
    <input type="hidden" value="${disk.id}" name="diskId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit">Save</button>
</form>
</@c.page>
