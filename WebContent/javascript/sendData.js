function send() {
	var title = document.getElementById("titolo").value;
	var description = document.getElementById("descrip").value;
	var mode = document.getElementById("mod").value;
	var categories = [];
	var elements = document.getElementsByClassName("cat-text");
	var refund = document.querySelector('input[name=refund]:checked').value;
	var date = document.getElementById("date").value;
	var time = document.getElementById("time").value;
	
	for(var i = 0; i < elements.length; ++i) {
		categories.push(elements[i].innerHTML);
	}
	
	if(title && description && elements.length > 0) {	
		$.ajax({
			url: "auctionCreation",
			type: "POST",
			contentTpe: false,
			cache: false,
			data: {
				"auctionTitle" : title,
				"auctionDescription" : description,
				"mod" : mode,
				"date" : date,
				"time" : time,
				"categories" : categories,
				"refund": refund
			},
			success: function() {
				window.location.href = "index";
			}
		});
	}
}