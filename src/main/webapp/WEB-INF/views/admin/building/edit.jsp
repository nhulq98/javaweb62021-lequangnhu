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
                                <form:input type="text" class="form-control" path="ward"/>
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Distrit</label>
                            <div class="col-sm-10 pull-right">
                                <div class="dropdown">
                                    <form:select path="district">
                                        <form:option value="" label="--- Chọn Quận ---"/>
                                        <form:options items="${district}" itemValue="code"
                                                      itemLabel="value"/>
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
                                <form:input type="number" class="form-control" path="rentPrice"/>
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label> Describe Rent price</label>
                            <div class="col-sm-10 pull-right">
                                    <%--              <form:input type="text" class="form-control" path="rentAreaDescription"/>--%>
                                <form:input type="text" class="form-control" path="rentPriceDescription"/>
                            </div>
                        </div>

                        <%--                        <div class="col-sm-12 form-group">--%>
                        <%--                            <label>Describe Area</label>--%>
                        <%--                            <div class="col-sm-10 pull-right">--%>
                        <%--                                        <form:input type="text" class="form-control" path="rentPriceDescription"/>--%>
                        <%--                            </div>--%>
                        <%--                        </div>--%>

                        <div class="col-sm-12 form-group">
                            <label>Service charge</label>
                            <div class="col-sm-10 pull-right">
                                <form:input type="number" class="form-control" path="serviceFee"/>
                            </div>
                        </div>

                        <div class=" col-sm-12 form-group">
                            <label>Car charge</label>
                            <div class="col-sm-10 pull-right">
                                <form:input type="number" class="form-control" path="carFee"/>
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Motorbike charge</label>
                            <div class="col-sm-10 pull-right">
                                <form:input type="number" class="form-control" path="motorbikeFee"/>
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Overtime charge</label>
                            <div class="col-sm-10 pull-right">
                                <form:input type="number" class="form-control" path="overtimeFee"/>
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Water charge</label>
                            <div class="col-sm-10 pull-right">
                                <form:input type="number" class="form-control" path="waterFee"/>
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Electricity charge</label>
                            <div class="col-sm-10 pull-right">
                                <form:input type="number" class="form-control" path="electricityFee"/>
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Deposits</label>
                            <div class="col-sm-10 pull-right">
                                <form:input type="number" class="form-control" path="deposit"/>
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Payment</label>
                            <div class="col-sm-10 pull-right">
                                <form:input type="number" class="form-control" path="payment"/>
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Rent time</label>
                            <div class="col-sm-10 pull-right">
                                <form:input type="number" class="form-control" path="rentTime"/>
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Decorator time</label>
                            <div class="col-sm-10 pull-right">
                                <form:input type="number" class="form-control" path="decoratorTime"/>
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Brokeragefee</label>
                            <div class="col-sm-10 pull-right">
                                <form:input type="number" class="form-control" path="brokerageFee"/>
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Note</label>
                            <div class="col-sm-10 pull-right">
                                <form:input type="number" class="form-control" path="note"/>
                            </div>
                        </div>

                        <%--                        <div class="col-sm-12 form-group">--%>
                        <%--                            <label>Link of building</label>--%>
                        <%--                            <div class="col-sm-10 pull-right">--%>
                        <%--                                <input type="text" class="form-control">--%>
                        <%--                            </div>--%>
                        <%--                        </div>--%>

                        <%--                        <div class="col-sm-12 form-group">--%>
                        <%--                            <label>Map</label>--%>
                        <%--                            <div class="col-sm-10 pull-right">--%>
                        <%--                                <input type="text" class="form-control">--%>
                        <%--                            </div>--%>
                        <%--                        </div>--%>

                        <%--                        <div class="col-sm-12 form-group">--%>
                        <%--                            <label>Image</label>--%>
                        <%--                            <div class="col-sm-10 pull-right">--%>
                        <%--                                <input type="image" class="form-control">--%>
                        <%--                            </div>--%>
                        <%--                        </div>--%>

                        <div class="col-sm-12 form-group">
                            <label>Rent Area</label>
                            <div class="col-sm-10 pull-right">
                                <form:input type="text" class="form-control" path="rentAreas"/>
                            </div>
                        </div>

                        <div class="col-sm-12 form-group">
                            <label>Building Types</label>
                            <form>
                                <div class="col-sm-10 pull-right">
                                    <c:if test="${model.id != null}">
                                        <c:forEach var="item" items="${renttypeedit}">
                                            <input class="checkboxclass" type="checkbox" name="${item.value}" value="${item.code}" ${item.checked}>
                                            <label>${item.value}</label>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${model.id == null}">
                                        <c:forEach var="item" items="${renttype}">
                                            <input class="checkboxclass" type="checkbox" name="${item.value}" value="${item.code}">
                                            <label>${item.value}</label>
                                        </c:forEach>
                                    </c:if>
                                        <%--<form:checkboxes path="rentTypes2" items="${renttype}"--%>
                                        <%--itemValue="code" itemLabel="value" />--%>

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
    //loadBuildingType();
    function loadBuildingType(){
        var buildingId = $('#buildingId').val();
        if (buildingId != "") {//update
            //get BuildingTypes
            $.ajax({
                url: '/building/api/buildingtype/{id}',
                type: 'GET',
                dataType: "json", // define data type for output data from server
                data: JSON.stringify(data),
                contentType: "application/json", // define data type for input data server
                success: function (res) {
                    $.each(res, function(index, item) {
                        row += '<input type="checkbox" name="checkList" value=' + item.id + ' id="checkbox_' + item.id + '" class="check-box-element"' + item.checked + '/>';
                    });
                    $('#typeList').html(row);
                },
                error: function (res) {
                    console.log('failed');
                    console.log(res);
                }
            });
        }else{
            $.each(res, function(index, item) {
                row += '<td class="text-center"> <input type="checkbox" name="checkList" value=' + item.id + ' id="checkbox_' + item.id + '" class="check-box-element"' + item.checked + '/></td>';
            });
        }
    }


    $("#btnAddOrUpdateBuildings").click(function (event) {
        event.preventDefault();
        var formData = $("#formEdit").serializeArray();
        var data = {};
        $.each(formData, function (i, v) {
            data["" + v.name + ""] = v.value;
        });
        var SlectedList = new Array();
        $('input[class="checkboxclass"]:checked').each(function() {
            SlectedList.push(this.value);
        });
        data["rentTypes"] = SlectedList;
        if ($('#buildingId').val() != "") {//update
            var buildingId = $('#buildingId').val();
            data["id"] = buildingId;
            updateBuilding(data, buildingId);
        } else {//add
            $('#loading_image').show();
            addBuilding(data);
        }
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
                window.location.href = "<c:url value='/admin/building-list?message=add_success'/>";
            },
            error: function (res) {
                console.log('failed');
                window.location.href = "<c:url value='/admin/building-list?message=add_failed'/>";
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
                window.location.href = "<c:url value='/admin/building-list?message=edit_success'/>";
                console.log('success');
            },
            error: function (res) {
                console.log('failed');
                window.location.href = "<c:url value='/admin/building-list?message=edit_failed'/>";
                console.log(res);
            }
        });
    }

</script>
</body>
</html>