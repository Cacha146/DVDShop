<#import "parts/common.ftl" as c>

<@c.page>
<div class="container">

    <#list takenItems as item>
        <form action="/take/my/return" method="post">
            <div class="row">
                <div class="col-4">
        <#if item.disk.filename??>

            <img src="/img/${item.disk.filename}" class="img-fluid img-rounded">
        </#if>
                </div>
                <div class="col">
                    <h5 >${item.disk.name}</h5>
                    <p> ${item.disk.description}</p>
                    <input type="hidden" value="${item.disk.id}" name="diskId">
                    <input type="hidden" value="${_csrf.token}" name="_csrf">
                    <button type="submit" class="btn btn-primary">Return disk</button>
                </div>
            </div>
        </form>
    <#else>
   <h3 class="mt-3"> No taken disk</h3>
    </#list>

</div>
</@c.page>