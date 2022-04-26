$(document).ready(function(){
	const menu = document.querySelector(".menu");
	const navOpen = document.querySelector(".hamburger");
	const navClose = document.querySelector(".close");
	const navBar = document.querySelector(".nav");
	
	const navLeft = menu.getBoundingClientRect().left;
	navOpen.addEventListener("click", () => {
	  if (navLeft < 0) {
	    menu.classList.add("show");
	    document.body.classList.add("show");
	    navBar.classList.add("show");
	  }
	});
	
	navClose.addEventListener("click", () => {
	  if (navLeft < 0) {
	    menu.classList.remove("show");
	    document.body.classList.remove("show");
	    navBar.classList.remove("show");
	  }
	});
	
	$('#exampleModal').on('show.bs.modal', function (event) {
	  var button = $(event.relatedTarget) // Button that triggered the modal
	  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
	  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
	  var modal = $(this)
	})
	
	
	$('#alertPageSuccess').show('fade');
	
	setTimeout(function () {
		$('#alertPageSuccess').hide('fade');
	},3000);
	
	$('#linkClose').click(function(){
		$('#alertPageSuccess').hide('fade');
	})	
})

function chosseFileImage(event){
	$(document).ready(function(){
		const imgNoteOldNone = document.querySelector("#imgNoteOldNone");
		$(imgNoteOldNone).hide();
	})
}

function setImgNoteOld(){
	$(document).ready(function(){
		const imgNoteOldNone = document.querySelector("#imgNoteOldNone");
		$(imgNoteOldNone).show();
	})
}

