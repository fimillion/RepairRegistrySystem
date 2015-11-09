<html>
<head>
    <title></title>
    <%-- JQuery --%>
    <%@include file="/include/jquery_include.jsp" %>
    <!-- Bootstrap Core JavaScript -->
    <%@include file="/include/bootstrup_include.jsp" %>
    <%@include file="/include/jgrid_include.jsp" %>

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

        $("#grid").jqGrid({
            url:'${pageContext.request.contextPath}/malfunctions/listing',
            editurl:'${pageContext.request.contextPath}/malfunctions/edit',
            datatype: 'json',
            mtype: 'POST',
            styleUI : 'Bootstrap',
            colNames:['Id', 'Name', 'Version'],
            colModel:[
                {name:'directoryId',index:'directoryId', width:55, editable:false, editoptions:{readonly:true, size:10}, hidden:true},
                {name:'directoryName',index:'directoryName', width:100, editable:true, editrules:{required:true}, editoptions:{size:10}},
                {name:'version',index:'version', width:100, editable:true, editrules:{readonly:true}, editoptions:{size:10,defaultValue:'0'}, hidden:true}
            ],
            rowNum:10,
            rowList:[10,20,40,60],
            height: 240,
            autowidth: true,
            rownumbers: false,
            pager: '#pager',
            sortname: 'directoryId',
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
                id: "directoryId"
            }
        });
        $("#grid").jqGrid('navGrid','#pager',
                {edit:true, add:true, del:true, search:false},
                {/*MOD PARAM*/
                    closeAfterEdit: true,
                    serializeEditData:function (data) {
                        if(data.id=="_empty")data.id=null;
                        data.directoryId=data.id;
                        return data;
                    }
                },
                {/*ADD PARAM*/
                    closeOnEscape: true,
                    closeAfterAdd: true,
                    serializeEditData:function (data) {
                        if(data.id=="_empty")data.id=null;
                        data.directoryId=data.id;
                        return data;
                    }
                },
                {/*DEL PARAM*/
                    serializeEditData:function (data) {
                        if(data.id=="_empty")data.id=null;
                        data.directoryId=data.id;
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