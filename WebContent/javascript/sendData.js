function send() {
	var title = document.getElementById("titolo").value;
	var description = document.getElementById("descrip").value;
	var mode = document.getElementById("mod").value;
	var categories = [];
	var elements = document.getElementsByClassName("cat-text");
	var refund = document.querySelector('input[name=refund]:checked').value;
	
	var file = document.getElementById("pic").files[0];
	
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
				"categories" : categories,
				"refund": refund
			},
			success: function() {
				window.location.href = "index";
				alert("Asta creata correttamente!");
			}
		});
	}
}