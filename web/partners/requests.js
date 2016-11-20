//window.onload = loadRestaurants();
window.onload = loadOUI();
function loadOUI(){
    var oModel = new sap.ui.model.json.JSONModel();
      
        /***** LOADING THE DATA *****/
        // load data from URL
        //oModel.loadData('http://mydomain/teamdetails_ui5.php?t=6'); 
        // OR set data manually
        oModel.setData(data);
      
      
        // register model in core
	sap.ui.getCore().setModel(oModel);

        // create your table
		var oTable1 = new sap.ui.table.Table({  
          title : "Players List",  
          visibleRowCount : 3,  
          selectionMode : sap.ui.table.SelectionMode.Single,  
          navigationMode : sap.ui.table.NavigationMode.Paginator,
          // bind the core-model to this table by aggregating player-Array
          rows: '{/player}'
        });

      // define the columns and the control templates to be used    
      oTable1.addColumn(new sap.ui.table.Column({  
        label : new sap.ui.commons.Label({  
          text : "Name"  
        }),  
        template : new sap.ui.commons.TextView({
          text: '{name}'
        }),
        width : "10px"  
      }));
      
      //place at DOM
      oTable1.placeAt('content');  
}
function loadRestaurants2() {

    var request = new XMLHttpRequest();
    request.open('GET', '/B-Recuerdos/api/products', true);

    request.onload = function () {
        if (this.status >= 200 && this.status < 400) {
            
            var data = JSON.parse(this.response);
            data.forEach(function(element, index){
                //document.getElementById('prueba').innerHTML += 'index: ' + element. + ' value: ' + value + ' <br>';
                
            })
            alert('listo');
        } else {
            // We reached our target server, but it returned an error
            alert('noles');

        }
    };

    request.onerror = function () {
        // There was a connection error of some sort
    };

    request.send();

}


