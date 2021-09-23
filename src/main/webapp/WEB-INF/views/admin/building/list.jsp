<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="formUrl" value="/admin/building-list"/>
<c:url var="buildingAPI" value="/api/building"/>
<c:url var="buildingEdit" value="/admin/building-edit"/>
<c:url var="updateAssignmentAPI" value="/api/building/assignmentbuilding"/>
<%--<c:url var="formAjax" value="/api/user"/>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <%--        <spring:message code="label.user.list"/>--%>
        Danh sách Tòa nhà
    </title>
    <style type="text/css">
        .btn-size {
            width: 78px !important;
        }

        select#staffStr
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
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="<c:url value="/admin/home"/>">
                        <%--<spring:message code="label.home"/>--%>
                        Trang chủ
                    </a>
                </li>
                <li class="active">
                    <%--<spring:message code="label.user.list"/>--%>
                    Danh sách tòa nhà
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
                                        Tìm kiếm
                                    </h4>
                                    <div class="widget-toolbar">
                                        <a href="#" data-action="collapse">
                                            <i class="ace-icon fa fa-chevron-up"></i>
                                        </a>
                                    </div>
                                </div>
                                <form:form commandName="buildingSearchForm" action="${formUrl}" id="listForm"
                                           method="GET">
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <div class="form-horizontal">
                                            <br class="form-group">
                                            <!-- Row 1-->
                                            <div class="col-sm-6">
                                                <div>
                                                    <label>Building name</label>
                                                    <form:input type="text" cssClass="form-control" path="name"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-6">
                                                <div>
                                                    <label>Floor Erea</label>
                                                    <form:input type="number" cssClass="form-control"
                                                                path="floorArea"/>
                                                </div>
                                            </div>

                                            <!-- ROW 2-->
                                            <div class="col-sm-4" id="profile" style="top: 25px; left: 10px;">
                                                <div class="form-group">
                                                    <div class="col-sm-9 no-padding-left">
                                                        <form:select path="district">
                                                            <form:option value="" label="--- Chọn Quận ---"/>
                                                            <form:options items="${district}" var="mapItem"
                                                                          itemValue="code"
                                                                          itemLabel="value"/>
                                                        </form:select>

                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label>Ward</label>
                                                    <form:input type="text" cssClass="form-control" path="ward"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label>Street</label>
                                                    <form:input type="text" cssClass="form-control" path="street"/>
                                                </div>
                                            </div>
                                            <!-- ROW 3-->
                                            <div class="col-sm-4">
                                                <div>
                                                    <label>Basement Number</label>
                                                    <form:input type="number" cssClass="form-control"
                                                                path="numberOfBasement"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label>Direction</label>
                                                    <form:input type="number" cssClass="form-control"
                                                                path="direction"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label>Level</label>
                                                    <form:input type="text" cssClass="form-control" path="level"/>
                                                </div>
                                            </div>
                                            <!-- ROW 4-->
                                            <div class="col-sm-3">
                                                <div>
                                                    <label>Area From</label>
                                                    <form:input type="number" cssClass="form-control"
                                                                path="rentAreaFrom"/>
                                                </div>
                                            </div>

                                            <div class="col-sm-3">
                                                <div>
                                                    <label for="rentAreaTo">Area to</label>
                                                    <form:input type="number" cssClass="form-control"
                                                                path="rentAreaTo"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div>
                                                    <label for="rentPriceFrom">Cost Rent from</label>
                                                    <form:input type="number" cssClass="form-control"
                                                                path="rentPriceFrom"/>
                                                </div>
                                            </div>


                                            <div class="col-sm-3">
                                                <div>
                                                    <label for="rentPriceTo">Cost Rent to</label>
                                                    <form:input type="number" cssClass="form-control"
                                                                path="rentPriceTo"/>
                                                </div>
                                            </div>
                                            <!-- ROW 5-->
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="managerName">Manager name</label>
                                                    <form:input type="text" cssClass="form-control"
                                                                path="managerName"/>
                                                </div>
                                            </div>

                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="managerPhone">Manager phone number</label>
                                                    <form:input type="number" cssClass="form-control"
                                                                path="managerPhone"/>
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

                                            <div class="col-sm-12 pull-right">
                                                <form:checkboxes path="rentTypes" items="${renttype}"
                                                                 itemValue="code" itemLabel="value"/>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"></label>
                                                <div class="col-sm-8">
                                                    <button id="btnSearch" type="button"
                                                            class="btn btn-sm btn-success">
                                                            <%--spring:message code="label.search"/>--%>
                                                        Tìm kiếm
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
                                           title="Thêm Tòa Nhà"
                                           href='<c:url value="/admin/building-edit"/>'>
                                            <span>
                                                <i class="fa fa-plus-circle bigger-110 purple"></i>
                                            </span>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="table-responsive">
                                <display:table name="buildingResult"
                                               requestURI="${formUrl}" id="item"
                                               class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                               style="margin: 3em 0 1.5em;">
                                    <display:column title="<fieldset class='form-group'>
												        <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>" class="center select-cell"
                                                    headerClass="center select-cell">
                                        <%--                                        <fieldset>--%>
                                        <%--                                            <input type="checkbox" name="checkList" value="${item.id}"--%>
                                        <%--                                                   id="checkbox_${item.id}" class="check-box-element"/>--%>
                                        <%--                                        </fieldset>--%>
                                    </display:column>

                                    <display:column headerClass="text-left" property="createdDate" title="ngày"/>
                                    <display:column headerClass="text-left" property="name"
                                                    title="Tên Sản Phẩm"/>
                                    <display:column headerClass="text-left" property="address" title="Địa Chỉ"/>
                                    <display:column headerClass="text-left" property="managerName"
                                                    title="Tên Quản Lý"/>
                                    <display:column headerClass="text-left" property="managerPhone"
                                                    title="Số Điện Thoại"/>
                                    <display:column headerClass="text-left" property="floorArea" title="DT Sàn"/>
                                    <display:column headerClass="text-left" title="DT Trống"/>
                                    <display:column headerClass="text-left" property="rentPrice" title="Giá Thuê"/>
                                    <display:column headerClass="text-left" property="serviceFee"
                                                    title="Phí Dịch Vụ"/>
                                    <display:column headerClass="text-left" title="Phí MG"/>
                                    <display:column headerClass="col-actions" title="Thao tác">
                                        <button onclick="assignmentBuilding(this.value)" value="${item.id}"
                                                type="button" class="btn btn-success btn-sm popup"
                                                data-toggle="tooltip; modal" title="Delevery Buildings!"
                                                data-target="#myModal" id="btnAssingmentBuilding">
                                            <i class="ace-icon fa fa-tasks"></i>
                                        </button>
                                        <input type="hidden" id="buildingIdCurrent" value="${item.id}"/>
                                        <a class="btn btn-info btn-sm" data-toggle="tooltip" title="Cập nhật tòa nhà"
                                           href='<c:url value="${buildingEdit}-${item.id}"/>'>
                                            <i class="fa fa-pencil-square-o"></i>
                                        </a>
                                        <%--                                        <button onclick="editBuilding(this.value)" value="${item.id}" type="button"--%>
                                        <%--                                                class="btn btn-info btn-sm"--%>
                                        <%--                                                data-toggle="tooltip"--%>
                                        <%--                                                title="Edit Buildings!">--%>
                                        <%--                                            <i class="ace-icon fa fa-pencil bigger-110"></i>--%>
                                        <%--                                        </button>--%>
                                        <button onclick="deleteBuilding(this.value)" value="${item.id}"
                                                id="btnDelete" type="button"
                                                class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
                                                data-toggle="tooltip"
                                                title="Xóa Tòa Nhà">
                                                <span>
                                                    <i class="fa fa-trash-o bigger-110 pink"></i>
                                                </span>
                                        </button>
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
        <div class="modal fade" id="assingmentBuildingModal" role="dialog">
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

                        <button type="button" class="btn btn-default" data-dismiss="modal"
                                onclick="assignmentForStaffs()">Assingment Building
                        </button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<!--BEGIN Script dialog -->
<script>
    // functions for assignment building
    function assignmentBuilding(id) {
        showListStaffById(id);
        $('#buildingIdCurrent').val(id); // set value for update staff function
        openModalAssingmentBuilding();
    }

    function openModalAssingmentBuilding() {

        $('#assingmentBuildingModal').modal();
    }

    function showListStaffById(buildingId) {
        $.ajax({
            url: '${buildingAPI}/' + buildingId + '/staffs',
            type: 'GET',
            dataType: "json", // define data type for output data from server
            //data: JSON.stringify(dataArray),
            //contentType: "text/plain", // define data type for input data server
            success: function (res) {
                var row = '';
                $.each(res, function (index, item) {
                    row += '<tr>';
                    row += '<td class="text-center"> <input type="checkbox" name="checkList" value=' + item.id + ' id="checkbox_' + item.id + '" class="check-box-element"' + item.checked + '/></td>';
                    row += '<td class="text-center">' + item.fullName + '</td>';
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
        var idArray = $('.check-box-element:checkbox:checked').map(function () {
            return this.value;
        }).get();
        var object = {};
        object.buildingId = $('#buildingIdCurrent').val();//get value
        object.staffIds = idArray;
        updateStaff(object);
    }

    function updateStaff(object) {
        $.ajax({
            url: '${updateAssignmentAPI}',
            type: 'POST',
            //dataType: "json", // define data type for output data from server
            data: JSON.stringify(object),
            contentType: "application/json", // define data type for input data server
            success: function (res) {
                console.log('success');
            },
            error: function (res) {
                console.log("Failed!" + res.toString());
            }
        });
    }

    //==============================================================


    //==================functions for building ===========================
    function deleteBuilding(buildingId) {
        showAlertBeforeDelete(function () {
            $.ajax({
                url: '${buildingAPI}/' + buildingId,
                type: 'DELETE',
                success: function (res) {
                    console.log('success');
                },
                error: function (res) {
                    console.log("Failed!" + res.toString());
                }
            });
        });
    }

    function editBuilding(id) {
        //var object = {"id": id};
        $.ajax({
            url: '${buildingEdit}-' + id,
            type: 'GET',
            //data: JSON.stringify(object),
            //contentType: "application/json", // define data type for input data server
            success: function (res) {
                console.log('success');
            },
            error: function (res) {
                console.log('failed');
                console.log(res);
            }
        });
    }
</script>
<!--END Script dialog -->
</body>

</html>