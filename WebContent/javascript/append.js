function append(text) {
	if(text !== "") {
		var node = document.createElement("DIV");
		var textnode = document.createTextNode(text);
		var del = document.createElement("DIV");
		var txt = document.createElement("DIV");
		
		node.classList.add("category");
		txt.classList.add("cat-text");
		txt.appendChild(textnode);
		node.appendChild(txt);
		
		del.classList.add("remove");
		del.innerHTML = "&times;";
		del.setAttribute("onclick", "remove(this)");
		node.appendChild(del);
		document.getElementById("category-container").appendChild(node);
	}
}

function remove(element) {
	element.parentNode.remove();
}