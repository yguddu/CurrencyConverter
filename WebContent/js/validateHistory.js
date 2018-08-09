 $(document).ready(function() {
     //$(':input[type="submit"]').prop('disabled', true);
     $(':input[type="date"]').on('mouseup keyup',function(){
    	 var dateFrom = document.getElementById('fromdate').value;
    	 var dateTo = document.getElementById('todate').value; 
    	 //if(dateFrom == 0 || dateTo == 0)
    	 if(!Date.parse(dateFrom) && !Date.parse(dateTo))
    	 {
    		 $(':input[type="submit"]').prop('disabled', true);
    	 }
    	 else
    	 {
    		 $(':input[type="submit"]').prop('disabled', false);
    	 }
    });
});
