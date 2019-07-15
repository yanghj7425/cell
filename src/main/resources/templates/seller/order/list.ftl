<html>
<head>
   <#include "../../common/header.ftl">
</head>

<body>

<#--sidber-->
<#--<#include "../../common/nav.ftl">-->
<#--content-->
<div id="page-content-wrapper">
    <div class="container-fluid">
        <div class="row clearfix">
            <div class="col-md-9 column">
                <table class="table">
                    <thead>
                    <tr>
                        <th> 订单ID</th>
                        <th> 姓名</th>
                        <th> 电话</th>
                        <th> 地址</th>
                        <th> 金额</th>
                        <th> 订单状态</th>
                        <th> 支付状态</th>
                        <th> 创建时间</th>
                        <th colspan="2"> 操作</th>
                    </tr>
                    </thead>
                    <tbody>
                <#list orderPage.getList() as item>
                <tr>
                    <td> ${ item.orderId ?c } </td>
                    <td> ${ item.buyerName } </td>
                    <td> ${ item.buyerPhone } </td>
                    <td> ${ item.buyerAddress } </td>
                    <td> ${ item.buyerAmount } </td>
                    <td> ${ item.getOrderStatusEnum().getMsg() } </td>
                    <td> ${ item.getPayStatusEnum().getMsg() } </td>
                    <td> ${ item.createTime ? string('yyyy-MM-dd HH:mm:ss')}</td>
                    <td>
                        <a href="/sell/seller/order/detail?orderId=${item.orderId ? c}">详情</a>
                    </td>
                    <td>
                            <#if item.getOrderStatus() ==0>
                                <a href="/sell/seller/order/cancel?orderId=${item.orderId ? c}">取消</a>
                            </#if>
                    </td>
                </tr>
                </#list>
                    </tbody>
                </table>
            </div>

            <div class="col-md-12 column">
                <ul class="pagination pull-right">
                <#if orderPage.isIsFirstPage()>
                        <li class="disabled"><a href="#">上一页</a></li>
                <#else>
                      <li>
                          <a href="/sell/seller/order/list?pageNum=${orderPage.getPrePage()}&pageSize=${orderPage.getPageSize()}">上一页</a>
                      </li>
                </#if>

                 <#list orderPage.getNavigatepageNums() as item>
                     <#if orderPage.getPageNum() == item>
                         <li class="disabled"><a href="#">${item}</a></li>
                     <#else>
                         <li>
                             <a href="/sell/seller/order/list?pageNum=${item}&pageSize=${orderPage.getPageSize()}">${item}</a>
                         </li>
                     </#if>
                 </#list>

                 <#if orderPage.isIsLastPage()>
                        <li class="disabled"><a href="#">下一页</a></li>
                 <#else>
                      <li>
                          <a href="/sell/seller/order/list?pageNum=${orderPage.getNextPage()}&pageSize=${orderPage.getPageSize()}">上一页</a>
                      </li>
                 </#if>
                </ul>
            </div>
        </div>
    </div>
</div>

<#--弹窗-->
<div class="modal fade" id="alertModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒
                </h4>
            </div>
            <div class="modal-body">
                你有一个新订单
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary">查看</button>
            </div>
        </div>

    </div>

</div>

<script type="text/javascript" src="http://cdn.staticfile.org/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/twitter-bootstrap/3.0.1/js/bootstrap.min.js"></script>
<script>
    var webSocket = null;
    if ('WebSocket' in window) {
        webSocket = new WebSocket('ws://127.0.0.1:8080/sell/webSocket');
    } else {
        // 如果不支持
    }

    webSocket.onopen = function (ev) {
        console.log("建立连接");
    }

    webSocket.onclose = function (ev) {
        console.log('连接关闭');
    }

    webSocket.onmessage = function (ev) {
        console.log('收到消息' + ev.data);
        $("#alertModal").modal('show');
    }

    webSocket.onerror = function (ev) {
        console.log('通信错误');
    }
    window.beforeunload = function () {
        webSocket.close();
    }


</script>
</body>
</html>

