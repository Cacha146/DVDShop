<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/main" class="form-inline">
            <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search disk by name">
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form>
    </div>
</div>

<#if isAdmin> <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Add new Disk
</a>
<div class="collapse <#if disk??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')}"
                       value="<#if disk??>${disk.name}</#if>" name="name" placeholder="Disk's name" />
                <#if nameError??>
                <div class="invalid-feedback">
                    ${nameError}
                </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control"
                       value="<#if disk??>${disk.description}</#if>" name="description" placeholder="Disk's description">
                <#if descriptionError??>
                    <div class="invalid-feedback">
                        ${descriptionError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Add</button>
            </div>
        </form>
    </div>
</div>
</#if>
<div class="card-columns">
    <#list disks as disk>
    <form>
        <div class="card my-3">
        <#if disk.filename??>
        <img src="/img/${disk.filename}" class="card-img-top">
        </#if>
        <div class="m-2">
            <span>${disk.name}</span>
            <i>${disk.description}</i>
            <#if isAdmin>
            <a href="/main/delete/${disk.id}" class="badge badge-danger">DELETE disk</a>
            <a href="/main/update/${disk.id}" class="badge badge-success">UPDATE disk</a>
            </#if>
        </div>
    </div>
    </form>
    <#else>
   <H3 class="mt-3"> No disks </H3>
    </#list>
</div>
</@c.page>