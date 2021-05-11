$(document).ready(function()
{
 $("#alertSuccess").hide();
 $("#alertError").hide();
});


	// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
		
	{
		// Clear alerts---------------------
		 $("#alertSuccess").text("");
		 $("#alertSuccess").hide();
		 $("#alertError").text("");
		 $("#alertError").hide();
		 
		 
		// Form validation-------------------
		var status = validateItemForm();
		
		if (status != true)
		 {
			 $("#alertError").text(status);
			 $("#alertError").show();
			 return;
		 }
		
		
		// If valid------------------------
		var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
		
		 $.ajax(
		 {
			 url : "ItemsAPI",
			 type : type,
			 data : $("#formItem").serialize(),
			 dataType : "text",
			 complete : function(response, status)
		 {
				 onItemSaveComplete(response.responseText, status);
		 }
		 });
		});

function onItemSaveComplete(response, status)
{
	if (status == "success")
	 {
		var resultSet = JSON.parse(response);
		
	 if (resultSet.status.trim() == "success")
	 {
		 $("#alertSuccess").text("Successfully saved.");
		 $("#alertSuccess").show();
		 
		 $("#divItemsGrid").html(resultSet.data);
	 } else if (resultSet.status.trim() == "error")
	 {
		 $("#alertError").text(resultSet.data);
		 $("#alertError").show();
	 }
	 } else if (status == "error")
	 {
		 $("#alertError").text("Error while saving.");
		 $("#alertError").show();
	 } else
	 {
		 $("#alertError").text("Unknown error while saving..");
		 $("#alertError").show();
	 } 
	
	 $("#hidItemIDSave").val("");
	 $("#formItem")[0].reset();
}

$(document).on("click", ".btnUpdate", function(event)
		{
		$("#hidItemIDSave").val($(this).data("ProductID"));
		$("#ProductID").val($(this).closest("tr").find('td:eq(0)').text());
		 $("#ProductName").val($(this).closest("tr").find('td:eq(1)').text());
		 $("#ProductDesc").val($(this).closest("tr").find('td:eq(2)').text());
		 $("#ProductReg").val($(this).closest("tr").find('td:eq(3)').text());
		 $("#ProductPrice").val($(this).closest("tr").find('td:eq(4)').text());
		 $("#InventorID").val($(this).closest("tr").find('td:eq(5)').text());
		});

$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "ItemsAPI",
		 type : "DELETE",
		 data : "ProductID=" + $(this).data("ProductID"),
		 dataType : "text",
		 complete : function(response, status)
		 {
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



//CLIENT-MODEL================================================================
function validateItemForm()
{
//CODE
//	if ($("#ProductID").val().trim() == "")
//	{
//	return "Insert ProductID.";
//	}
	// NAME
	if ($("#ProductName").val().trim() == "")
	{
	return "Insert Product Name.";
}

//PRICE-------------------------------
if ($("#ProductDesc").val().trim() == "")
{
return "Insert Product Desc.";
}

//is numerical value
var tmpPrice = $("#ProductPrice").val().trim();
if (!$.isNumeric(tmpPrice))
{
return "Insert a numerical value for Item Price.";
}

//convert to decimal price
//$("#ProductPrice").val(parseFloat(tmpPrice).toFixed(2));

//InventorID------------------------
if ($("#InventorID").val().trim() == "")
{
return "Insert InventorID.";
}
return true;
}


/*
// If valid----------------------
	var student = getStudentCard($("#txtName").val().trim(),
	 $('input[name="rdoGender"]:checked').val(),
	 $("#ddlYear").val()); 
	
	$("#colStudents").append(student);
	
	 $("#alertSuccess").text("Saved successfully.");
	 $("#alertSuccess").show();
	
	 $("#formStudent")[0].reset();
	});
	
	// REMOVE==========================================
	$(document).on("click", ".remove", function(event)
	{
	 $(this).closest(".student").remove();
	
	 $("#alertSuccess").text("Removed successfully.");
	 $("#alertSuccess").show();
	});
	
// CLIENT-MODEL=================================================================
	function validateItemForm()
	{
	// NAME
	if ($("#txtName").val().trim() == "")
	 {
		return "Insert student name.";
	 }
	// GENDER
	if ($('input[name="rdoGender"]:checked').length === 0)
	 {
		return "Select gender.";
	 }
	// YEAR
	if ($("#ddlYear").val() == "0")
	 {
		return "Select year.";
	 }
	return true;
	}
	
	
	function getStudentCard(name, gender, year)
	{
	var title = (gender == "Male") ? "Mr." : "Ms.";
		var yearNumber = "";
		switch (year) {
		case "1":
			yearNumber = "1st";
		 break;
		case "2":
			yearNumber = "2nd";
		 break;
		case "3":
			yearNumber = "3rd";
		 break;
		case "4":
			yearNumber = "4th"; 
		
		break;
	 }
		
		var student = "";
	
		 student += "<div class=\"student card bg-light m-2\" style=\"max-width: 10rem; float: left;\">";
		 student += "<div class=\"card-body\">" ;
		 student += title + " " + name + ",";
		 student += "<br>";
		 student += yearNumber + " year";
		 student += "</div>";
		 student += "<input type=\"button\" value=\"Remove\"class=\"btn btn-danger remove\">";
		 student += "</div>";
	return student;
	}

*/