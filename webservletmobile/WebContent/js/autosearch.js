var path = window.location.pathname;
  $(function() {
	  
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
        .append( "<a href=\" /mobiles/"+ item.urlTitle +/*"-"+item.mobileId+*/"\" >"  + item.title + "</a>" )
 //       .appendTo( "</div>" )
        .appendTo( ul );
    };
 }
  });
  
  

  $(function() {
    $( "#project1").keyup(function() {
    	var queryString = $( "#project1" ).val();
    	console.log("project1:::"+queryString);
    	//alert(queryString);
    	 $.ajax({
     		   type: "GET",
     		   dataType: "json",
     		   url: "/queryString?q="+queryString,
     		   success: function(jsonData){
     		    console.log(jsonData);
     		    loadingAutoComplete1(jsonData.mobileVoList);
     		   }
     		});
    });
    
    loadingAutoComplete1("");
    
function loadingAutoComplete1(data){
    $( "#project1" ).autocomplete({
      minLength: 0,
      source: data,
      focus: function( event, ui ) {
    	  console.log(ui.item.label);
        $( "#project1" ).val( ui.item.label );
        return false;
      },
      select: function( event, ui ) {
        $( "#project1" ).val( ui.item.label );
        return false;
      }
    })
    .autocomplete( "instance" )._renderItem = function( ul, item ) {
      return $( "<li>" )
        .append( "<a href=\"" + path +"/" + item.urlTitle +/*"-"+item.mobileId+*/"\" >"  + item.title + "</a>" )
        .appendTo( ul );
    };
 }
  });
  
  
  
  $(function() {
	  
	    $( "#project2").keyup(function() {
	    	var queryString = $( "#project2" ).val();
	    	console.log("project2:::"+queryString);
	    	//alert(queryString);
	    	 $.ajax({
	     		   type: "GET",
	     		   dataType: "json",
	     		   url: "/queryString?q="+queryString,
	     		   success: function(jsonData){
	     		    console.log(jsonData);
	     		    loadingAutoComplete2(jsonData.mobileVoList);
	     		   }
	     		});
	    });
	    
	    loadingAutoComplete2("");
	    
	function loadingAutoComplete2(data){
	    $( "#project2" ).autocomplete({
	      minLength: 0,
	      source: data,
	      focus: function( event, ui ) {
	    	  console.log(ui.item.label);
	        $( "#project2" ).val( ui.item.label );
	        return false;
	      },
	      select: function( event, ui ) {
	        $( "#project2" ).val( ui.item.label );
	        return false;
	      }
	    })
	    .autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<li>" )
	        .append( "<a href=\"" + path  + vsPath + item.urlTitle +/*"-"+item.mobileId+*/"\" >"  + item.title + "</a>" )
	        .appendTo( ul );
	    };
	 }
	  });
  

  $(function() {
	  
	    $( "#project3").keyup(function() {
	    	var queryString = $( "#project3" ).val();
	    	console.log("project3:::"+queryString);
	    	//alert(queryString);
	    	 $.ajax({
	     		   type: "GET",
	     		   dataType: "json",
	     		   url: "/queryString?q="+queryString,
	     		   success: function(jsonData){
	     		    console.log(jsonData);
	     		    loadingAutoComplete3(jsonData.mobileVoList);
	     		   }
	     		});
	    });
	    
	    loadingAutoComplete3("");
	    
	function loadingAutoComplete3(data){
	    $( "#project3" ).autocomplete({
	      minLength: 0,
	      source: data,
	      focus: function( event, ui ) {
	    	  console.log(ui.item.label);
	        $( "#project3" ).val( ui.item.label );
	        return false;
	      },
	      select: function( event, ui ) {
	        $( "#project3" ).val( ui.item.label );
	        return false;
	      }
	    })
	    .autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<li>" )
	        .append( "<a href=\"" + path + vsPath + item.urlTitle +/*"-"+item.mobileId+*/"\" >"  + item.title + "</a>" )
	        .appendTo( ul );
	    };
	 }
	  });
  

  $(function() {
	  
	    $( "#project4").keyup(function() {
	    	var queryString = $( "#project4" ).val();
	    	console.log("project4:::"+queryString);
	    	//alert(queryString);
	    	 $.ajax({
	     		   type: "GET",
	     		   dataType: "json",
	     		   url: "/queryString?q="+queryString,
	     		   success: function(jsonData){
	     		    console.log(jsonData);
	     		    loadingAutoComplete4(jsonData.mobileVoList);
	     		   }
	     		});
	    });
	    
	    loadingAutoComplete4("");
	    
	function loadingAutoComplete4(data){
	    $( "#project4" ).autocomplete({
	      minLength: 0,
	      source: data,
	      focus: function( event, ui ) {
	    	  console.log(ui.item.label);
	        $( "#project4" ).val( ui.item.label );
	        return false;
	      },
	      select: function( event, ui ) {
	        $( "#project4" ).val( ui.item.label );
	        return false;
	      }
	    })
	    .autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<li>" )
	        .append( "<a href=\"" + path + vsPath + item.urlTitle +/*"-"+item.mobileId+*/"\" >"  + item.title + "</a>" )
	        .appendTo( ul );
	    };
	 }
	  });