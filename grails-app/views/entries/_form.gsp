<%@ page import="com.mwu.Entries" %>



<div class="fieldcontain ${hasErrors(bean: entriesInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="entries.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${entriesInstance?.name}"/>
</div>

