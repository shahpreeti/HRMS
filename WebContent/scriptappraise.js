function countRate(r_id,star,trid)
{
	
	var labelid=document.getElementById(r_id.id);
	var tr_id=document.getElementById(trid.id);
	document.getElementById(labelid.id).innerHTML=star;
	document.getElementById(tr_id.id).value=star;
	
	for(var i=1;i<=star;i++)
		{	var b_id="b"+i+labelid.id;
			document.getElementById(b_id).innerHTML ='<img src="star.png" />'; 
		
		}
	for(var i=5;i>star;i--)
	{	var b_id="b"+i+labelid.id;
		document.getElementById(b_id).innerHTML ='<img src="starUnfilled.png" />'; 
	
	}
	
}
function loadForm(section)
{
	var s="formsection"+section;	
	var elements = document.getElementsByClassName("formsection");
	
    for(var i = 0, length = elements.length; i < length; i++) {
      if(elements[i].id==s)
    	{
    	  elements[i].style.display = 'block';
    	}
          
      else
    	  elements[i].style.display = 'none';
      
    }
    var elements1 = document.getElementsByClassName("tablinks");
      var num=Number(section);
      for(var i = 0, length = elements1.length; i < length; i++) {
    	
          if(elements1[i].id=="Section Name"+num)
        	  {
        	  	if(i==length-1)
            	  document.getElementById("submission").style.display='block';
        	  	else
        	      document.getElementById("submission").style.display='none';
        	  	elements1[i].style.backgroundColor = "#ddd";
        	  }
          else
        	  elements1[i].style.backgroundColor = "#f1f1f1";
          }	
      document.getElementById("indicator").style.display='none';
      
}
function loadCriteria(loadrow)
{
	var elements1 = document.getElementsByClassName("indicator_row");
	for(var i = 0, length = elements1.length; i < length; i++) 
	{
    	if(elements1[i].id==loadrow)
    		document.getElementById(loadrow).style.display='table-row';	
    	else
		document.getElementById(elements1[i].id).style.display='none';
   		
	}
	
}
function loadSelfForm(row_id)
{
	var ans=document.getElementById(row_id).style.display==='table-row';
    	if(ans)
    		document.getElementById(row_id).style.display='none';	
    	else
    		document.getElementById(row_id).style.display='table-row';
   		
}
function loadSelfForm2(row_id)
{
	var ans=document.getElementById(row_id).style.display==='block';
    	if(ans)
    		document.getElementById(row_id).style.display='none';	
    	else
    		document.getElementById(row_id).style.display='block';
   		
}
