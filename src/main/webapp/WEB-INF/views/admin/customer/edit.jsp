<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/taglib.jsp" %>
<c:url value="/admin/customer-list" var="customerList" />
<c:url value="/admin/customer-edit" var="customerEdit" />
<c:url value="/api/customer" var="customerAPI" />
<c:url value="/api/customer/transaction" var="transactionURL"/>
<html>
<head>
    <title>Chỉnh sửa khách hàng </title>
</head>btnAddTransaction
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="<c:url value="/admin/home"/>">
                        <%--<spring:message code="label.home"/>--%>
                        Home
                    </a>
                </li>
                <li class="active">Edit Customer</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content" style="margin-bottom: -25px;">
            <div class="page-header" style="padding: 3px">
                <h1 style="font-size: 21px;">Customer information</h1>
            </div>
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">

                    <form:form  class="form-horizontal" role="form" id="formSubmitCustomer" modelAttribute="customer" >
                        <div class="form-group">
                            <label for="fullName" class="col-sm-3 control-label no-padding-right"> Tên đầy đủ : </label>
                            <div class="col-sm-7">
                                <form:input path="fullName" cssClass="form-control" />

                            </div>
                        </div>

                        <div class="form-group">
                            <label  class="col-sm-3 control-label no-padding-right" for="phone" >Số điện thoại :</label>
                            <div class="col-sm-7">
                                <form:input path="phone" cssClass="form-control" />

                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-3 control-label no-padding-right"  for="email" >Email : </label>
                            <div class="col-sm-7">
                                <form:input path="email" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-3 control-label no-padding-right"  for="company" >Tên công ty : </label>
                            <div class="col-sm-7">
                                <form:input path="company" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-3 control-label no-padding-right"  for="demand" >Nhu cầu  : </label>
                            <div class="col-sm-7">
                                <form:input path="demand" cssClass="form-control" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label  class="col-sm-3 control-label no-padding-right" for="note" >Ghi chú :</label>
                            <div class="col-sm-7">
                                <form:textarea path="note" rows="4" cols="69" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-3 control-label no-padding-right"  for="status" >Trạng thái  : </label>
                            <div class="col-sm-7">
                                <form:input path="status" cssClass="form-control" />
                            </div>
                        </div>
                        <form:hidden path="id" id="customerid"/>
                            <div class="col-md-offset-3 col-md-9">
                                <c:if test="${not empty customer.id}">
                                    <button class="btn btn-info" type="button" id="btnAddOrUpdateCustomer">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                        Update customer
                                    </button>
                                </c:if>

                                <c:if test="${empty customer.id}">
                                    <button class="btn btn-info" type="button" id="btnAddOrUpdateCustomer">
                                        <i class="ace-icon fa fa-check bigger-110"></i>
                                       Add customer
                                  </button>
                                </c:if>
                                <%--<button class="btn" type="reset">--%>
                                    <%--<i class="ace-icon fa fa-undo bigger-110"></i>--%>
                                    <%--Destroy--%>
                                <%--</button>--%>
                            </div>
                    </form:form> <%-- end form --%>
                </div>
            </div>
        </div>

        <%-- Transactions --%>
        <c:forEach  var="item" items="${transactiontype}">
            <div class="page-content">
                <div class="page-header" style="padding: 3px">
                    <h1  style="font-size: 21px;" >${item.name}
                        <button value="${item.code}"
                                class="dt-button buttons-colvis btn btn-white btn-primary btn-bold btnAddTransaction"
                                data-toggle="tooltip"  title='Thêm hành động' >
                            <span><i class="fa fa-plus-circle sbigger-110 purple"></i></span>
                        </button>
                    </h1>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                <tr style="font-family: VnBlack ; font-weight: bold; color: #000000;">
                                    <td class="col-sm-3">Ngày tạo</td>
                                    <td>Ghi chú</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="index" items="${transaction}">
                                    <c:if test="${item.code.equals(index.code)}">
                                        <tr>
                                            <%--<input type="hidden" id="type_${index.typeId}"/>--%>
                                            <th>${index.createdDate}</th>
                                            <th>${index.note}</th>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                                <tr>
                                    <th></th>
                                    <th>

                                        <input type="text" style="width: 100%" name="node" id="${item.code}"/>

                                    </th>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </div>
        </c:forEach>
    </div>
</div>

<script >
    $('#btnAddOrUpdateCustomer').click(function (e){
        e.preventDefault();
        var data ={};
        var formdata = $('#formSubmitCustomer').serializeArray();
        $.each(formdata , function (i,v){
            data[""+v.name+""] = v.value;
        })
        var id = $('#customerid').val();
        if (id == ''){
            addCustomer(data);

        }else{
            updateCustomer(data);
        }
    })
    function  addCustomer(data){
        $.ajax({
            url : '${customerAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                console.log(result)
                window.location.href = "${customerList}?message=add_success'"
            },
            error: function (error) {
                console.log(error)
                window.location.href = "${customerList}?message=add_failed'"
            }
        });
    }
    function  updateCustomer(data){
        $.ajax({
            url : '${customerAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                console.log(result)
                window.location.href = "${customerList}?message=update_success'"
            },
            error: function (error) {
                console.log(error)
                window.location.href = "${customerList}?message=update_faild'"
            }
        });
    }



    $('.btnAddTransaction').click(function () {
        var data ={};
        let noteObject = document.getElementById(this.value);
        data['note'] = noteObject.value;
        data['customerId'] = $('#customerid').val();
        data['code'] = this.value;
        addTransaction(data);
    })

    function  addTransaction(data){
        $.ajax({
            url : '${transactionURL}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${customerEdit}-" + data['customerId'];
            },
            error: function (error) {
                window.location.href = "${customerEdit}-" + data['customerId'];
            }
        })
    }

</script>

</body>
</html>
