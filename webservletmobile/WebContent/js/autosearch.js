var path = window.location.pathname;
 /* $(function() {
	  
    var projects ;
    var queryString;
    
    $( "#project").keyup(function() {
    	queryString = $( "#project" ).val();
    	//alert(queryString);
    	 $.ajax({
     		   type: "GET",
     		   dataType: "json",
     		   url: "/queryString?q="+queryString,
     		   success: function(jsonData){
     		    console.log(jsonData);
     		    loadingAutoComplete(jsonData.mobileVoList);
     		   }
     		});
    	 $.ajax({
   		   type: "GET",
   		   dataType: "json",
   		   url: "/querystring?q="+queryString,
   		   success: function (data, text) {
   			console.log("data::"+data);
   			console.log("text::"+text);
   		 loadingAutoComplete(data.mobileVoList);
   	    },
   		   error: function (request, status, error) {
   			console.log(request.responseText);
   			console.log(status);
   			console.log(error);
   	    }
   		});
    });
    
    loadingAutoComplete("");
    
function loadingAutoComplete(data){
    $( "#project" ).autocomplete({
      minLength: 0,
      source: data,
      focus: function( event, ui ) {
    	  console.log(ui.item.label);
        $( "#project" ).val( ui.item.label );
        return false;
      },
      select: function( event, ui ) {
        $( "#project" ).val( ui.item.label );
        return false;
      }
    })
    .autocomplete( "instance" )._renderItem = function( ul, item ) {
      return $( "<li>" )
  //      .appendTo( "<div class=\"auto-search-div\" >" )
	//	.append( "<img src=\" http://biqcdn.com.s3.amazonaws.com/m/img/p/p96617-pi201293-ci0-ui166760-s360x360.jpg /" + item.icon + " \" />" )
        .append( "<a href=\" /mobiles/"+ item.urlTitle +"-"+item.mobileId+"\" >"  + item.title + "</a>" )
 //       .appendTo( "</div>" )
        .appendTo( ul );
    };
 }
  });
  
    */

$(function() {
    $( "#project").keyup(function() {
    	var queryString = $( "#project" ).val();
    	console.log("project:::"+queryString);
    	var data = titleArray;
    	loadingAutoComplete(data);
    });
    
    loadingAutoComplete("");
    
function loadingAutoComplete(data){
    $( "#project" ).autocomplete({
      minLength: 0,
      source: function(request, response) {
          var results = $.ui.autocomplete.filter(data, request.term);
          response(results.slice(0, 10));
      },
      focus: function (event, ui) {
			$(event.target).val(ui.item.title);
			return false;
	},
	select: function (event, ui) {
		$(event.target).val(ui.item.title);
		var title = ui.item.title;
		window.location	= "/mobiles/" + title.toLowerCase().split(" ").join("-");
		return false;
	}
    })
 }
});

 $(function() {
	    $( "#project1").keyup(function() {
	    	var queryString = $( "#project1" ).val();
	    	console.log("project1:::"+queryString);
	    	var data = titleArray;
	    	loadingAutoComplete1(data);
	    });
	    
	    loadingAutoComplete1("");
	    
	function loadingAutoComplete1(data){
	    $( "#project1" ).autocomplete({
	      minLength: 0,
	      source: function(request, response) {
	          var results = $.ui.autocomplete.filter(data, request.term);
	          response(results.slice(0, 10));
	      },
	      focus: function (event, ui) {
				$(event.target).val(ui.item.title);
				return false;
		},
		select: function (event, ui) {
			$(event.target).val(ui.item.title);
			var title = ui.item.title;
			window.location	= path + "/" + title.toLowerCase().split(" ").join("-");
			return false;
		}
	    })
	 }
   });
  
  
   $(function() {
	    $( "#project2").keyup(function() {
	    	var queryString = $( "#project2" ).val();
	    	console.log("project2:::"+queryString);
	    	var data = titleArray;
	    	loadingAutoComplete2(data);
	    });
	    
	    loadingAutoComplete2("");
	    
	function loadingAutoComplete2(data){
	    $( "#project2" ).autocomplete({
	      minLength: 0,
	      source: function(request, response) {
	          var results = $.ui.autocomplete.filter(data, request.term);
	          response(results.slice(0, 10));
	      },
	      focus: function (event, ui) {
				$(event.target).val(ui.item.title);
				return false;
		},
		select: function (event, ui) {
			$(event.target).val(ui.item.title);
			var title = ui.item.title;
			window.location	= path + vsPath + title.toLowerCase().split(" ").join("-");
			return false;
		}
	    })
	 }
   });

   $(function() {
	    $( "#project3").keyup(function() {
	    	var queryString = $( "#project3" ).val();
	    	console.log("project3:::"+queryString);
	    	var data = titleArray;
	    	loadingAutoComplete3(data);
	    });
	    
	    loadingAutoComplete3("");
	    
	function loadingAutoComplete3(data){
	    $( "#project3" ).autocomplete({
	      minLength: 0,
	      source: function(request, response) {
	          var results = $.ui.autocomplete.filter(data, request.term);
	          response(results.slice(0, 10));
	      },
	      focus: function (event, ui) {
				$(event.target).val(ui.item.title);
				return false;
		},
		select: function (event, ui) {
			$(event.target).val(ui.item.title);
			var title = ui.item.title;
			window.location	= path + vsPath + title.toLowerCase().split(" ").join("-");
			return false;
		}
	    })
	 }
   });
   
   
  $(function() {
	    $( "#project4").keyup(function() {
	    	var queryString = $( "#project4" ).val();
	    	console.log("project4:::"+queryString);
	    	var data = titleArray;
	    	loadingAutoComplete4(data);
	    });
	    
	    loadingAutoComplete4("");
	    
	function loadingAutoComplete4(data){
	    $( "#project4" ).autocomplete({
	      minLength: 0,
	      source: function(request, response) {
	          var results = $.ui.autocomplete.filter(data, request.term);
	          response(results.slice(0, 10));
	      },
	      focus: function (event, ui) {
				$(event.target).val(ui.item.title);
				return false;
		},
		select: function (event, ui) {
			$(event.target).val(ui.item.title);
			var title = ui.item.title;
			window.location	= path + vsPath + title.toLowerCase().split(" ").join("-");
			return false;
		}
	    })
	 }
	  });
  
  $(function() {
	  
	    $( "#project5").keyup(function() {
	    	var queryString = $( "#project5" ).val();
	    	console.log("project5:::"+queryString);
	    	var data = titleArray;
	    	loadingAutoComplete5(data);
	    });
	    
	    loadingAutoComplete5("");
	  });
  
  function loadingAutoComplete5(data){
  
  $( "#project5" ).autocomplete({
      source: function(request, response) {
          var results = $.ui.autocomplete.filter(data, request.term);
          response(results.slice(0, 10));
      },
      //autoFocus:true,
      focus: function (event, ui) {
			$(event.target).val(ui.item.title);
			return false;
		},
		select: function (event, ui) {
			$(event.target).val(ui.item.title);
			var title = ui.item.title;
			window.location  = "/mobiles/compare/" + $( "#mobileTitle" ).val() +  "-vs-" + title.toLowerCase().split(" ").join("-");;
			return false;
		}
   });
  }