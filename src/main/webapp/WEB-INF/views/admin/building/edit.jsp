<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingAPI" value="/api/building"/>
<html>
<head>
    <title>Chỉnh sửa Tòa Nhà</title>
</head>
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
                    <a href="#">Trang chủ</a>
                </li>
                <li class="active">Chỉnh sửa Tòa Nhà</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">

                    <form:form class="form-horizontal" role="form" id="formEdit" commandName="model">
                        <!-- PAGE CONTENT BEGINS -->

                        <!-- Row 1-->
                        <div class="col-sm-12 form-group">
                            <label>Building name</label>
                            <div class="col-sm-10 pull-right">
                                <form:input type="text" class="form-control" path="name"/>
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Manager Name</label>
                            <div class="col-sm-10 pull-right">
                                <form:input type="text" class="form-control" path="managerPhone"/>
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Manager Phone</label>
                            <div class="col-sm-10 pull-right">
                                <form:input type="text" class="form-control" path="managerName"/>
                            </div>
                        </div>

                        <div class="space-4"></div>
                        <div class="col-sm-12 form-group">
                            <label>Street</label>
                            <div class="col-sm-10 pull-right">
                                <form:input type="text" class="form-control" path="street"/>
                            </div>
                        </div>

                        <div class="space-4"></div>
                        <div class="col-sm-12 form-group">
                            <label>Ward</label>
                            <div class="col-sm-10 pull-right">
                                <input type="text" class="form-control">
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Distrit</label>
                            <div class="col-sm-10 pull-right">
                                <div class="dropdown">
                                    <form:select path="districtID">
                                        <form:option value="" label="--- Chọn Quận ---"/>
                                        <form:options items="${district}" itemValue="id"
                                                      itemLabel="name"/>
                                    </form:select>
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Structure</label>
                            <div class="col-sm-10 pull-right">
                                <form:input type="text" class="form-control" path="structure"/>
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Basement Numbers</label>
                            <div class="col-sm-10 pull-right">
                                <form:input type="number" class="form-control" path="numberOfBasement"/>
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Floor Area</label>
                            <div class="col-sm-10 pull-right">
                                <form:input type="number" class="form-control" path="floorArea"/>
                            </div>
                        </div>


                        <div class="col-sm-12 form-group">
                            <label>Direction</label>
                            <div class="col-sm-10 pull-right">
                                <form:input type="text" class="form-control" path="direction"/>
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Level</label>
                            <div class="col-sm-10 pull-right">
                                <form:input type="text" class="form-control" path="level"/>
                            </div>
                        </div>
                        <div class="col-sm-12 form-group">
                            <label>Rent price</label>
                            <div class="col-sm-10 pull-right">
                                <input type="text" class="form-control">
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label> Describe Rent price</label>
                            <div class="col-sm-10 pull-right">
                                    <%--              <form:input type="text" class="form-control" path="rentAreaDescription"/>--%>
                                <input type="text" class="form-control">
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Describe Area</label>
                            <div class="col-sm-10 pull-right">
                                    <%--                            <form:input type="text" class="form-control" path="rentAreaDescription"/>--%>
                                <input type="text" class="form-control">
                            </div>
                        </div>


                        <div class="col-sm-12 form-group">
                            <label>Service charge</label>
                            <div class="col-sm-10 pull-right">
                                <input type="number" class="form-control">
                            </div>
                        </div>

                        <div class=" col-sm-12 form-group">
                            <label>Car charge</label>
                            <div class="col-sm-10 pull-right">
                                <input type="number" class="form-control">
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Motorbike charge</label>
                            <div class="col-sm-10 pull-right">
                                <input type="number" class="form-control">
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Overtime charge</label>
                            <div class="col-sm-10 pull-right">
                                <input type="number" class="form-control">
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Water charge</label>
                            <div class="col-sm-10 pull-right">
                                <input type="number" class="form-control">
                            </div>
                        </div>


                        <div class="col-sm-12 form-group">
                            <label>Electricity charge</label>
                            <div class="col-sm-10 pull-right">
                                <input type="number" class="form-control">
                            </div>
                        </div>


                        <div class="col-sm-12 form-group">
                            <label>Deposits</label>
                            <div class="col-sm-10 pull-right">
                                <input type="number" class="form-control">
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Payment</label>
                            <div class="col-sm-10 pull-right">
                                <input type="number" class="form-control">
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Rent time</label>
                            <div class="col-sm-10 pull-right">
                                <input type="text" class="form-control">
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Decorator time</label>
                            <div class="col-sm-10 pull-right">
                                <input type="text" class="form-control">
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Brokeragefee</label>
                            <div class="col-sm-10 pull-right">
                                <input type="text" class="form-control">
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Note</label>
                            <div class="col-sm-10 pull-right">
                                <input type="text" class="form-control">
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Link of building</label>
                            <div class="col-sm-10 pull-right">
                                <input type="text" class="form-control">
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Map</label>
                            <div class="col-sm-10 pull-right">
                                <input type="text" class="form-control">
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Image</label>
                            <div class="col-sm-10 pull-right">
                                <input type="image" class="form-control">
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Building Types</label>
                            <form>
                                <div class="col-sm-10 pull-right">
                                    <form:checkboxes path="listType" items="${renttype}"
                                                     itemValue="code" itemLabel="name"/>
                                </div>
                            </form>
                        </div>

                        <div class="clearfix form-group">
                            <div class="col-md-offset-8 col-sm-9">
                                <button class="btn btn-info" id="btnAddOrUpdateBuildings" type="button">
                                    <i class="ace-icon fa fa-check bigger-110"></i>
                                    Submit
                                </button>
                                &nbsp; &nbsp; &nbsp;
                                <button class="btn" type="reset">
                                    <i class="ace-icon fa fa-undo bigger-110"></i>
                                    Reset
                                </button>
                            </div>
                        </div>
                        <form:hidden path="id" id="buildingId"/>
                    </form:form>
                </div>

            </div><!-- /.col -->
            <!--END page CONTENT-->
        </div><!-- ROW-->
    </div>
</div>
<script>
    $("#btnAddOrUpdateBuildings").click(function (event) {
        event.preventDefault();
        var formData = $("#formEdit").serializeArray();
        var data = {};
        $.each(formData, function (i, v) {
            data["" + v.name + ""] = v.value;
        });
        if ($('#buildingId').val() != "") {//update
            var buildingId = $('#buildingId').val();
            //updateUser(data, $('#buildingId').val());
        } else {//add
            $('#loading_image').show();
            addBuilding(data);
        }
    });

    $('#btnResetPassword').click(function (event) {
        event.preventDefault();
        $('#loading_image').show();
        resetPassword($('#userId').val());
    });

    function addBuilding(data) {
        $.ajax({
            url: '${buildingAPI}',
            type: 'POST',
            dataType: "json", // define data type for output data from server
            data: JSON.stringify(data),
            contentType: "application/json", // define data type for input data server
            success: function (res) {
               console.log('success');
            },
            error: function (res) {
                console.log('failed');
                console.log(res);
            }
        });
    }

    function updateBuilding(data, id) {
        $.ajax({
            url: '${buildingAPI}/' + id,
            type: 'PUT',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
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
</body>
</html>