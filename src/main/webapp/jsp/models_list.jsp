<html>
<head>
    <title></title>
    <%-- JQuery --%>
    <%@include file="/include/jquery_include.jsp" %>
    <!-- Bootstrap Core JavaScript -->
    <%@include file="/include/bootstrup_include.jsp" %>
    <%@include file="/include/jgrid_include.jsp" %>
    <%-- jquery.ajax-combobox --%>
    <%@include file="/include/select_include.jsp" %>
</head>
<body>
<div id="wrapper">
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <%@include file="/base_element/top_header.jsp" %>
        <%@include file="/base_element/left_menu.jsp" %>
    </nav>
</div>

<div id="page-wrapper">
    <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/"><c:message code="label.main"/></a></li>
        <li class="active"><c:message code="label.globaldirectory.documenttype"/></li>
    </ol>
    <div class="row">

        <div class="col-lg-12">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><c:message code="label.globaldirectory.documenttype.title"/></h3>
                </div>
                <div class="panel-body">
                    <div>
                        <table id="grid"></table>
                        <div id="pager"></div>
                    </div>
                </div>
            </div>

        </div>
        <!-- /.col-lg-12 -->
    </div>
</div>
</body>
<script type="text/javascript">
    $(document).ready(function () {

        var select_obj={};
        var GridData={
            brand_id:function(){return select_obj.brand_id},
            devicetype_id:function(){return select_obj.devicetype_id},
        };

        $("#grid").jqGrid({
            url:'${pageContext.request.contextPath}/models/listing',
            editurl:'${pageContext.request.contextPath}/models/edit',
            datatype: 'json',
            mtype: 'POST',
            styleUI : 'Bootstrap',
            colNames:['Id', 'Name','<c:message code="label.models.deviceType.title"/>','Brand','price', 'Version'],
            colModel:[
                {name:'modelId',index:'modelId', width:55, editable:false, editoptions:{readonly:true, size:10}, hidden:true},
                {name:'modelName',index:'modelName', width:100, editable:true, editrules:{required:true}, editoptions:{size:10}},
                fairwind_select_column('deviceType','/devicetypes/showList','',select_obj,{
                    show_field: 'deviceTypeName',
                    id:'deviceTypeId',
                    select_params:{
                        parameter_name:'devicetype_id',
                        name:'deviceTypeId',

                    },
                    select_plugin_options: {
                        sub_info: true,
                        sub_as: {
                            deviceTypeDescription: 'Description',
                        },
                        show_field: 'deviceTypeName, deviceTypeDescription'
                    },
                    post_parameter_name:'devicetype_id',

                }),
                fairwind_select_column('brand','/brands/showList','',select_obj,{
                    show_field: 'brandName',
                    id:'brandId',
                    select_params:{
                        parameter_name:'brand_id',
                        name:'brandId'
                    },
                    post_parameter_name:'brand_id',
                    select_plugin_options: {
                        sub_info: true,
                        sub_as: {
                            brandDescription: 'Description',
                            brandCounry: 'Country'
                        },
                        show_field: 'brandName, brandDescription, brandCounry'
                    },

                }),
                {name:'price',index:'price', width:100, editable:true,search: false, editrules:{required:true,integer:true}, editoptions:{size:10}},
                {name:'version',index:'version', width:100, editable:true, editrules:{readonly:true}, editoptions:{size:10,defaultValue:'0'}, hidden:true}
            ],
            rowNum:10,
            rowList:[10,20,40,60],
            height: 240,
            autowidth: true,
            rownumbers: false,
            pager: '#pager',
            sortname: 'modelId',
            viewrecords: true,
            sortorder: "asc",
            caption:"Records",
            emptyrecords: "Empty records",
            loadonce: false,
            altRows:true,
            loadComplete: function() {},
            jsonReader : {
                root: "rows",
                page: "page",
                total: "total",
                records: "records",
                repeatitems: false,
                cell: "cell",
                id: "modelId"
            }
        });
        $("#grid").jqGrid('navGrid','#pager',
                {edit:true, add:true, del:true, search:false},
                {/*MOD PARAM*/
                    closeAfterEdit: true,
                    editData:GridData,
                    serializeEditData:function (data) {
                        if(data.id=="_empty")data.id=null;
                        data.modelId=data.id;
                        return data;
                    }
                },
                {/*ADD PARAM*/
                    closeOnEscape: true,
                    closeAfterAdd: true,
                    editData:GridData,
                    serializeEditData:function (data) {
                        if(data.id=="_empty")data.id=null;
                        data.modelId=data.id;
                        return data;
                    }
                },
                {/*DEL PARAM*/
                    serializeEditData:function (data) {
                        if(data.id=="_empty")data.id=null;
                        data.modelId=data.id;
                        return data;
                    }
                },
                { 	// search
                    sopt:['cn', 'eq', 'ne', 'lt', 'gt', 'bw', 'ew'],
                    closeOnEscape: true,
                    multipleSearch: true,
                    closeAfterSearch: true,
                    multipleGroup:true
                }
        );

        jQuery("#grid").jqGrid('filterToolbar',{stringResult: false,searchOnEnter:true});


    });
</script>
</html>