
<%@ page import="com.mwu.Entries" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'entries.label', default: 'Entries')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<r:require modules="bootstrap"/>
	</head>
	<body>
		<div id="list-entries" class="content scaffold-list container" role="main">
			<div class="row" style="text-align:center;"><h3>Raffle</h3></div>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="name" title="${message(code: 'entries.name.label', default: 'Name')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${entriesInstanceList}" status="i" var="entriesInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><div id="row_${i}" style="text-transform:uppercase;">${fieldValue(bean: entriesInstance, field: "name")}</td></div>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="row"><div class="span2 offset1"><button id="draw" class="btn">Draw</button></div></div>
		</div>
<script>

$('#draw').click(function(){
	var row = 10
		
	$.ajax({
	  url: 'entries/draw.json',
	  success: function(data) {
	    var selected = data.selected
		var entries = data.list
		var curIndex
	
		$("#row_0").animate( {"font-size": "14px"}, 0 );

	    for (i=1; i<=entries.length; i++) {
	        setTimeout((function(i){
	            return function(){
					for (j=0; j<row; j++) {
						curIndex = i + j
						if (curIndex > entries.length-1) {
							curIndex = curIndex - entries.length 								
						}
						$("#row_"+j).html(entries[curIndex].name.toUpperCase())
						
						if (entries[curIndex].id === selected && j==0) {
							$("#row_0").animate( {"font-size": "30px"}, "fast" )
						}
					}
	            };
	        }(i)), i*250);
			
			if (entries[i].id === selected) {
				console.log('selected',selected + '==' + entries[i].id )
	            break;
	        }
	    }
	  }
	});
});
</script>
	</body>
</html>
