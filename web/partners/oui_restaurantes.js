/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var oModel = new sap.ui.model.json.JSONModel();

/***** LOADING THE DATA *****/
oModel.loadData('http://192.168.162.13/Restaurant/rest/restaurants');

//var data =
//        [
//            {"name": "Rajesh"},
//            {"name": "Kunal Jauhari"},
//            {"name": "Ashish Singh"},
//            {"name": "Ansuman Parhi"},
//            {"name": "Arup Kumar"},
//            {"name": "Deepak Malviya"},
//            {"name": "Seshu"},
//            {"name": "Ankush Datey"},
//            {"name": "Tapesh Syawaria"},
//            {"name": "Mahesh"},
//            {"name": "Vinay Joshi"},
//            {"name": "Ardhendu Karna"},
//            {"name": "Abhishek Shukla"},
//            {"name": "Kashim"},
//            {"name": "Vinayak"}
//        ]
//        ;



// register model in core
sap.ui.getCore().setModel(oModel);

// create your table
var oTable1 = new sap.ui.table.Table({
    title: "Restaurantes",
    visibleRowCount: 4,
    selectionMode: sap.ui.table.SelectionMode.Single,
    navigationMode: sap.ui.table.NavigationMode.Paginator,
    // bind the core-model to this table by aggregating player-Array
    rows: '{/}'
});

// define the columns and the control templates to be used    
oTable1.addColumn(new sap.ui.table.Column({
    label: new sap.ui.commons.Label({
        text: "Nombre"
    }),
    template: new sap.ui.commons.TextView({
        text: '{name}'
    }),
    width: "10px"
}));

oTable1.addColumn(new sap.ui.table.Column({
    label: new sap.ui.commons.Label({
        text: "Dirección"
    }),
    template: new sap.ui.commons.TextView({
        text: '{addres}'
    }),
    width: "10px"
}));

oTable1.addColumn(new sap.ui.table.Column({
    label: new sap.ui.commons.Label({
        text: "Teléfono"
    }),
    template: new sap.ui.commons.TextView({
        text: '{phone}'
    }),
    width: "10px"
}));

//place at DOM
oTable1.placeAt('prueba');
