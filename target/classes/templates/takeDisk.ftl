<#import "parts/common.ftl" as c>

<@c.page>
<div class="container">

    <#list disks as disk>
        <form action="/take" method="post">
            <div class="row">
            <div class="col-4">
        <#if disk.filename??>

        <img src="/img/${disk.filename}" class="img-fluid img-rounded">
        </#if>
            </div>
                <div class="col">
                    <h5 >${disk.name}</h5>
                    <p> ${disk.description}</p>
                    <input type="hidden" value="${disk.id}" name="diskId">
                    <input type="hidden" value="${_csrf.token}" name="_csrf">
                    <button type="submit" class="btn btn-primary">Take disk</button>
                </div>
            </div>
        </form>
    <#else>
    <h3 class="mt-3"> No free disk</h3>
    </#list>

</div>
</@c.page>