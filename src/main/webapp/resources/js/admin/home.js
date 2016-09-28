$(document).ready(function() {
	var tableClient = $('#usersList').DataTable({
		"autoWidth": false,
		"columnDefs": [
			{"targets": [ 0 ],
				"visible": false,
				"searchable": false}
		],
		"ajax": {
			"url": "account/all",
			"type": "GET",
			"success" :  function(data){
				$.each(data, function(ind, obj){

					tableClient.row.add([
						obj.id,
						obj.email,
						obj.role,
						obj.created
					]).draw();
				});
			}
		},
	});
});