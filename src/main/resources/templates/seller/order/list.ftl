<html>
<head>
   <#include "../../common/header.ftl">
</head>

<body>

<div id="wrapper" class="toggled">
<#--sidber-->
<#include "../../common/nav.ftl">
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
</div>
</body>
</html>



bu'shu
