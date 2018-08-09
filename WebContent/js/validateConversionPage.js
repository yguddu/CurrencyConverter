 $(document).ready(function() {
     $(':input[type="text"]').on('mouseup keyup',function(){
    	 var amount = document.getElementById('amount1').value;
    	 if(isNaN(amount) || amount == 0)   	 {
    		 $(':input[id="submit"]').prop('disabled', true);
    	 }
    	 else
    	 {
    		 $(':input[id="submit"]').prop('disabled', false);
    	 }
    });
    /*$(':input[id="submit"]').click(function(){
     $(':input[id="convertValue"]').prop('disabled', false);
    	
     });*/s
});
