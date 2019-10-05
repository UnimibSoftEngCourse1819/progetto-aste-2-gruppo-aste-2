function append(text) {
	if(text !== "") {
		var node = document.createElement("DIV"); /* div principale */
		var textnode = document.createTextNode(text); /* testo */
		var del = document.createElement("DIV"); /* div per la x*/
		var txt = document.createElement("DIV"); /* div contenente il testo */
		
		var hidden = document.createElement("INPUT");
		hidden.setAttribute("type", "hidden");
		hidden.setAttribute("name", "categories");
		hidden.value = text;		
		
		node.classList.add("category");
		txt.classList.add("cat-text");
		txt.appendChild(textnode);
		node.appendChild(hidden);
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