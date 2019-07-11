<html>
<head>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
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
                           <td> ${ item.orderStatus } </td>
                           <td> ${ item.payStatus } </td>
                           <td> ${ item.createTime ? string('yyyy-MM-dd HH:mm:ss')}</td>
                       </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>




