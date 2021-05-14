
$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();
	$("#hidItemIDSave").val("");
	$("#formOrder")[0].reset();
});
 
 $(document).on("click", "#btnSave", function(event)
{ 
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide(); 

var status = validateItemForm(); 
if (status != true) 
 { 
	 $("#alertError").text(status); 
	 $("#alertError").show(); 
	 return; 
 } 
 

var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( { 
		 url : "OrderAPI", 
		 type : type, 
		 data : $("#formOrder").serialize(), 
		 dataType : "text", 
		 complete : function(response, status)  { 
		 	onItemSaveComplete(response.responseText, status); 
		 } 
 }); 
 
 
	 function onItemSaveComplete(response, status)
	 { 
		if (status == "success")  { 
			 var resultSet = JSON.parse(response); 
			 if (resultSet.status.trim() == "success")  { 
				 $("#alertSuccess").text("Successfully saved."); 
				 $("#alertSuccess").show(); 
				 $("#divItemsGrid").html(resultSet.data); 
			 } else if (resultSet.status.trim() == "error")  { 
				 $("#alertError").text(resultSet.data); 
				 $("#alertError").show(); 
			 } 
		 } else if (status == "error") { 
			 $("#alertError").text("Error while saving."); 
			 $("#alertError").show(); 
		 } else { 
			 $("#alertError").text("Unknown error while saving.."); 
			 $("#alertError").show(); 
		 }
		 	 $("#hidItemIDSave").val(""); 
	 		 $("#formOrder")[0].reset(); 
	 }
 
 
 
	 $(document).on("click", ".btnUpdate", function(event) { 
		 $("#hidItemIDSave").val($(this).data("orderID")); 
		 $("#orderCode").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#customerID").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#customerEmail").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#customerName").val($(this).closest("tr").find('td:eq(3)').text()); 
		 $("#orderTotalAmount").val($(this).closest("tr").find('td:eq(4)').text()); 
		 $("cardNo").val($(this).closest("tr").find('td:eq(5)').text()); 
		 $("#cvvNo").val($(this).closest("tr").find('td:eq(6)').text()); 
	 });
  
	 $(document).on("click", ".btnRemove", function(event) { 
		 $.ajax({ 
			 url : "OrderAPI", 
			 type : "DELETE", 
			 data : "orderID=" + $(this).data("orderID"),
			 dataType : "text", 
			 complete : function(response, status){ 
			 	onItemDeleteComplete(response.responseText, status); 
			 } 
		 }); 
	 });
 
 
 
 
	 function onItemDeleteComplete(response, status)
	 { 
		 if (status == "success") 
		 { 
		 	var resultSet = JSON.parse(response); 
			 if (resultSet.status.trim() == "success") 
			 { 
				 $("#alertSuccess").text("Successfully deleted."); 
				 $("#alertSuccess").show(); 
				 $("#divItemsGrid").html(resultSet.data); 
			 } 
			 else if (resultSet.status.trim() == "error") 
			 { 
				 $("#alertError").text(resultSet.data); 
				 $("#alertError").show(); 
			 } 
			 } 
			 else if (status == "error") 
			 { 
				 $("#alertError").text("Error while deleting."); 
				 $("#alertError").show(); 
			 } 
			 else
			 { 
				 $("#alertError").text("Unknown error while deleting.."); 
				 $("#alertError").show(); 
			 } 
	 }
 
 
	function validateItemForm() 
	{ 
		if ($("#orderCode").val().trim() == "") 
		 { 
		 	return "Insert the Order's Code."; 
		 } 
	
		if ($("#customerID").val().trim() == "") 
		 { 
		 	return "Insert the Customer ID."; 
		 } 
	
		if ($("#customerEmail").val().trim() == "") 
		 { 
		 	return "Insert the Customer's E-mail Address."; 
		 } 
	
		if ($("#customerName").val().trim() == "") 
		 { 
		 	return "Insert the Customer's Name."; 
		 }
		 if ($("#orderTotalAmount").val().trim() == "") 
		 { 
		 	return "Insert the Total Amount"; 
		 } 
		 if ($("#cardNo").val().trim() == "") 
		 { 
		 	return "Insert the Card Number"; 
		 }
		 if ($("#cvvNo").val().trim() == "") 
		 { 
		 	return "Insert the CVV Number"; 
		 }
		return true; 
	}

});

 