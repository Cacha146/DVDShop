<#import "parts/common.ftl" as c>

<@c.page>
<div class="container">

<div class="row">

    <div class="col-sm">
        <p >Name disk</p>
    </div>
    <div class="col-sm">
        <p>Username</p>
    </div>
    <div class="col-sm">
        <p>Date taken</p>
    </div>
    <div class="col-sm">
        <p>Date returned</p>
    </div>
    <div class="col-sm">
        <p>Operation</p>
    </div>
</div>

    <#list takenItems as item>

        <form action="/take/delete" method="post">
            <div class="row">

                <div class="col">
                    <p >${item.disk.name?ifExists}</p>
                </div>
                <div class="col">
                    <p>${item.user.username?ifExists}</p>
                </div>
                <div class="col">
                    <p>${item.date_taken?ifExists}</p>
                </div>
                <div class="col">
                    <p>${item.date_returned?ifExists}</p>
                </div>
                <div class="col"
                <input type="hidden" value="${item.id}" name="itemId">
                <input type="hidden" value="${_csrf.token}" name="_csrf">
                <button type="submit" class="badge badge-danger">Delete entry</button>
            </div>
            </div>
        </form>
    <#else>
    <h2 class="mt-3">No operation taken</h2>
    </#list>

</div>
</@c.page>