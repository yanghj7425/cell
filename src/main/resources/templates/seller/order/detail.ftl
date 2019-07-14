<html>
<head>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-4 column">
            <table class="table">
                <thead>
                <tr>
                    <th>
                        订单ID
                    </th>
                    <th>
                        总金额
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                    ${ order.orderId ?c}
                    </td>
                    <td>
                    ${order.buyerAmount}
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="col-md-12 column">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>
                        商品ID
                    </th>
                    <th>
                        商品名称
                    </th>
                    <th>
                        数量
                    </th>
                    <th>
                        总额
                    </th>
                </tr>
                </thead>
                <tbody>
                    <#list order.getOrderDetailList() as product>
                    <tr>
                        <td>
                            ${product.productId}
                        </td>
                        <td>
                            ${product.productName}
                        </td>
                        <td>
                            ${product.productQuantity }
                        </td>
                        <td>
                            ${product.productQuantity * product.productPrice}
                        </td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>

        <div class="col-md-12 column">
            <#if order.orderStatus == 0>
                <a href="/sell/seller/order/finish?orderId=${order.orderId?c}"
                type=" button"
                class="btn btn-default btn-primary">完结订单</a>
                <a href="/sell/seller/order/cancel?orderId=${order.orderId?c}"
                type=" button"
                class="btn btn-default btn-danger">取消订单</a>
            </#if>
        </div>

    </div>
</div>
</body>
</html>




