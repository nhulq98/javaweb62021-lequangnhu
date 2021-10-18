<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="CustomerList" value="/admin/customer-list"/>
<c:url var="CustomerAPI" value="/api/customer"/>
<c:url var="assignmentCustomerAPI" value="/api/customer/assignment"/>

<%--<c:url var="formAjax" value="/api/user"/>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><%--        <spring:message code="label.user.list"/>--%>
        Danh sách Tòa nhà
    </title>
    <style type="text/css">
        .btn-size {
            width: 78px !important;
        }

        select
        #staffStr
    </style>
</head>

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
                <li><i class="ace-icon fa fa-home home-icon"></i> <a
                        href="<c:url value="/admin/home"/>"> <%--<spring:message code="label.home"/>--%>
                    Trang chủ
                </a></li>
                <li class="active">
                    <%--<spring:message code="label.user.list"/>--%> Danh sách tòa nhà
                </li>
            </ul>
            <!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${messageResponse!=null}">
                        <div class="alert alert-block alert-${alert}">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="ace-icon fa fa-times"></i>
                            </button>
                                ${messageResponse}
                        </div>
                    </c:if>
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="widget-box table-filter">
                                <div class="widget-header">
                                    <h4 class="widget-title">
                                        <%--<spring:message code="label.search"/>--%>
                                        Search
                                    </h4>
                                    <div class="widget-toolbar">
                                        <a href="#" data-action="collapse"> <i
                                                class="ace-icon fa fa-chevron-up"></i>
                                        </a>
                                    </div>
                                </div>
                                <form:form commandName="customerSearchForm"
                                           action="${CustomerList}" id="listForm" method="GET">
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <div class="form-horizontal">
                                            <br class="form-group">
                                            <!-- Row 1-->
                                            <div class="col-sm-6">
                                                <div>
                                                    <label>Customer Name</label>
                                                    <form:input type="text" cssClass="form-control"
                                                                path="fullName"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-6">
                                                <div>
                                                    <label>Phone number</label>
                                                    <form:input type="text" cssClass="form-control"
                                                                path="phone"/>
                                                </div>
                                            </div>

                                            <div class="col-sm-6">
                                                <div>
                                                    <label>Email</label>
                                                    <form:input type="text" cssClass="form-control"
                                                                path="email"/>
                                                </div>
                                            </div>

                                            <div class="col-sm-4">
                                                <div class="form-group">
                                                    <div class="col-sm-9"
                                                         style="padding-top: 25px; padding-left: 19px;">
                                                        <form:select path="staffId">
                                                            <form:option value="" label="--- Chọn nhân viên ---"/>
                                                            <form:options items="${staff}" itemValue="id"
                                                                          itemLabel="fullName"/>
                                                        </form:select>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"></label>
                                                <div class="col-sm-8">
                                                    <button id="btnSearch" type="submit"
                                                            class="btn btn-sm btn-success">
                                                            <%--spring:message code="label.search"/>--%>
                                                        Search
                                                        <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            </form:form>
                            <div class="table-btn-controls">
                                <div class="pull-right tableTools-container">
                                    <div class="dt-buttons btn-overlap btn-group">
                                        <a flag="info"
                                           class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                           data-toggle="tooltip"
                                        <%--title='<spring:message code="label.user.add"/>'--%>
                                           title="Add Customer"
                                           href='<c:url value="/admin/customer-edit"/>'> <span>
													<i class="fa fa-plus-circle bigger-110 purple"></i>
											</span>
                                        </a>
                                    </div>
                                    <button onclick="deleteMutipleCustomer()"
                                            id="btnDeleteMutiple" type="button"
                                            class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
                                            data-toggle="tooltip" title="Delete Mutiple Customer">
												<span> <i class="fa fa-trash-o bigger-110 pink"></i>
												</span>
                                    </button>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="table-responsive">
                                <display:table name="result" requestURI="${CustomerList}"
                                               id="item"
                                               class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                               style="margin: 3em 0 1.5em;">
                                    <display:column
                                            title="<fieldset class='form-group'>
												        <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>"
                                            class="center select-cell" headerClass="center select-cell">
                                        <fieldset>
                                            <input type="checkbox" name="checkList" value="${item.id}"
                                                   id="checkbox_${item.id}" class="check-box-element"/>
                                        </fieldset>
                                    </display:column>

                                    <display:column headerClass="text-left" property="fullName"
                                                    title="Tên"/>
                                    <display:column headerClass="text-left" property="managerName"
                                                    title="Tên Quản Lý"/>
                                    <display:column headerClass="text-left"
                                                    property="phone" title="Số Điện Thoại"/>
                                    <display:column headerClass="text-left" property="email"
                                                    title="Email"/>
                                    <%--<display:column headerClass="text-left" property="demand" title="Nhu cầu" />--%>
                                    <display:column headerClass="text-left" property="createdBy" title="Người Nhập"/>
                                    <display:column headerClass="text-left" property="createdDate" title="Ngày Nhập"/>
                                    <display:column headerClass="text-left" property="status" title="Tình trạng"/>
                                    <display:column headerClass="col-actions" title="Thao tác">
                                        <div class="pull-right tableTools-container">
                                            <button onclick="assignmentCustomer(this.value)"
                                                    value="${item.id}" type="button"
                                                    class="btn btn-succcreateStaffs ess btn-sm popup"
                                                    data-toggle="tooltip; modal" title="Delevery Customer!"
                                                    data-target="#myModal" id="btnAssingmentCustomer">
                                                <i class="ace-icon fa fa-tasks"></i>
                                            </button>
                                            <input type="hidden" id="customerIdCurrent"
                                                   value="${item.id}"/>
                                            <a class="btn btn-info btn-sm" data-toggle="tooltip"
                                               title="Edit Customer"
                                               href='<c:url value="/admin/customer-edit-${item.id}"/>'>
                                                <i class="fa fa-pencil-square-o"></i>
                                            </a>
                                        </div>
                                        <%--<button onclick="deleteCustomer(this.value)"--%>
                                                <%--value="${item.id}" id="btnDelete" type="button"--%>
                                                <%--class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"--%>
                                                <%--data-toggle="tooltip" title="Delete Customer">--%>
												<%--<span> <i class="fa fa-trash-o bigger-110 pink"></i>--%>
												<%--</span>--%>
                                        <%--</button>--%>
                                    </display:column>
                                </display:table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div class="container">

        <!-- Modal -->
        <div class="modal fade" id="assingmentCustomerModal" role="dialog">
            <div class="modal-dialog modal-lg">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">List Employee</h4>
                    </div>
                    <div class="modal-body">
                        <table class="table table-bordered" id="staffList">
                            <thead>
                            <tr>
                                <th>Chọn nhân viên</th>
                                <th>Tên nhân viên</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>

                    </div>
                    <div class="modal-footer">

                        <button type="button" class="btn btn-default"
                                data-dismiss="modal" onclick="assignmentForStaffs()">Assingment
                            Building
                        </button>
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal">Close
                        </button>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<!--BEGIN Script dialog -->
<script>
    // functions for assignment customer
    function assignmentCustomer(id) {
        showListStaffById(id);
        $('#customerIdCurrent').val(id); // set value for update staff function
        openModalAssingmentCustomer();
    }

    function openModalAssingmentCustomer() {

        $('#assingmentCustomerModal').modal();
    }

    function showListStaffById(customerId) {
        $
            .ajax({
                url: '${assignmentCustomerAPI}/' + customerId + '/staffs',
                type: 'GET',
                dataType: "json", // define data type for output data from server
                //data: JSON.stringify(dataArray),
                //contentType: "text/plain", // define data type for input data server
                success: function (res) {
                    let row = '';
                    $.each(res, function (index, item) {
                        row += '<tr>';
                        row += '<td class="text-center"> <input type="checkbox" name="checkList" value=' + item.id + ' id="checkbox_' + item.id + '" class="check-box-element"' + item.checked + '/></td>';
                        row += '<td class="text-center">'
                            + item.fullName
                            + '</td>';
                    });
                    $('#staffList tbody').html(row);
                    console.log(res);
                    console.log('success');
                },
                error: function (res) {
                    console.log("Failed!" + res.toString());
                }
            });
    }

    function assignmentForStaffs() {
        let idArray = $('.check-box-element:checkbox:checked').map(
            function () {
                return this.value;
            }).get();
        let object = {};
        object.id = $('#customerIdCurrent').val();//get value
        object.staffIds = idArray;
        updateStaff(object);
    }

    function updateStaff(object) {
        $.ajax({
            url: '${assignmentCustomerAPI}',
            type: 'PUT',
            //dataType: "json", // define data type for output data from server
            data: JSON.stringify(object),
            contentType: "application/json", // define data type for input data server
            success: function (res) {
                window.location.href = "<c:url value='${CustomerList}?message=delete_success'/>";
            },
            error: function (res) {
                window.location.href = "<c:url value='${CustomerList}?message=delete_failed'/>";
            }
        });
    }

    //==============================================================

    //==================functions for customer ===========================
    function deleteCustomer(customerId) {
        let idArray = {};
        idArray.customerIds = customerId;
        showAlertBeforeDelete(function () {
            $
                .ajax({
                    url: '${CustomerAPI}',
                    type: 'DELETE',
                    contentType: 'application/json',
                    data: JSON.stringify(idArray),
                    dataType: 'json',
                    success: function (res) {
                        window.location.href = "<c:url value='${CustomerList}?message=delete_success'/>";
                    },
                    error: function (res) {
                        window.location.href = "<c:url value='${CustomerList}?message=delete_failed'/>";
                    }
                });
        });
    }

    function deleteMutipleCustomer(){
        // get all checked
        let ids = $('.check-box-element:checkbox:checked').map(
            function () {
                return this.value;
            }).get();
        let idArray = {};
        idArray.customerIds = ids;

        showAlertBeforeDelete(function () {
            $
                .ajax({
                    url: '${CustomerAPI}',
                    type: 'DELETE',
                    contentType: 'application/json',
                    data: JSON.stringify(idArray),
                    dataType: 'json',
                    success: function (res) {
                        window.location.href = "<c:url value='${CustomerList}?message=delete_success'/>";
                    },
                    error: function (res) {
                        window.location.href = "<c:url value='${CustomerList}?message=delete_failed'/>";
                    }
                });
        });
    }

</script>
<!--END Script dialog -->
</body>

</html>