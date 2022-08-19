/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 6;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 100.0, "KoPercent": 0.0};
    var dataset = [
        {
            "label" : "KO",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "OK",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.8405733186328556, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [0.9696666666666667, 500, 1500, "Enter internal ref number-0"], "isController": false}, {"data": [0.956, 500, 1500, "Enter internal ref number-1"], "isController": false}, {"data": [0.9516666666666667, 500, 1500, "contains_poisonous_ingredients-1"], "isController": false}, {"data": [0.0, 500, 1500, "Create a new product"], "isController": true}, {"data": [0.6836666666666666, 500, 1500, "add_physical_form"], "isController": false}, {"data": [0.97, 500, 1500, "64 \/sign-in-1"], "isController": false}, {"data": [0.95, 500, 1500, "64 \/sign-in-2"], "isController": false}, {"data": [0.5, 500, 1500, "64 \/sign-in-0"], "isController": false}, {"data": [0.685, 500, 1500, "select_frame_formulation"], "isController": false}, {"data": [0.94, 500, 1500, "contains_poisonous_ingredients-0"], "isController": false}, {"data": [0.4553333333333333, 500, 1500, "Upload product image"], "isController": false}, {"data": [0.98, 500, 1500, "60 \/sign-in"], "isController": false}, {"data": [0.9513333333333334, 500, 1500, "contains_special_applicator-1"], "isController": false}, {"data": [0.9453333333333334, 500, 1500, "contains_cmrs-1"], "isController": false}, {"data": [0.956, 500, 1500, "contains_special_applicator-0"], "isController": false}, {"data": [0.9443333333333334, 500, 1500, "contains_cmrs-0"], "isController": false}, {"data": [0.639, 500, 1500, "trigger_question\/select_ph_range"], "isController": false}, {"data": [0.9466666666666667, 500, 1500, "Product shades info-1"], "isController": false}, {"data": [0.9373333333333334, 500, 1500, "Product shades info-0"], "isController": false}, {"data": [0.7086666666666667, 500, 1500, "select_category?category=oral_hygiene_products"], "isController": false}, {"data": [0.964, 500, 1500, "select_category?category=oral_hygiene_products-0"], "isController": false}, {"data": [0.6993333333333334, 500, 1500, "Single or Multi_item"], "isController": false}, {"data": [0.953, 500, 1500, "select_category?category=oral_hygiene_products-1"], "isController": false}, {"data": [0.673, 500, 1500, "Product shades info"], "isController": false}, {"data": [0.5643333333333334, 500, 1500, "87 \/responsible_persons\/3\/notifications\/new"], "isController": false}, {"data": [0.94, 500, 1500, "landing page - unsigned"], "isController": true}, {"data": [0.9323333333333333, 500, 1500, "Enter Product name-0"], "isController": false}, {"data": [0.9516666666666667, 500, 1500, "Enter Product name-1"], "isController": false}, {"data": [0.23, 500, 1500, "SigningIn 2fa"], "isController": true}, {"data": [0.6876666666666666, 500, 1500, "contains_special_applicator"], "isController": false}, {"data": [0.6853333333333333, 500, 1500, "select_category?category=tooth_whiteners"], "isController": false}, {"data": [0.6973333333333334, 500, 1500, "contains_nanomaterials"], "isController": false}, {"data": [0.6913333333333334, 500, 1500, "select_special_applicator_type"], "isController": false}, {"data": [0.6883333333333334, 500, 1500, "Enter Product name"], "isController": false}, {"data": [0.693, 500, 1500, "contains_poisonous_ingredients"], "isController": false}, {"data": [0.728, 500, 1500, "Enter internal ref number"], "isController": false}, {"data": [0.9523333333333334, 500, 1500, "Upload product image-2"], "isController": false}, {"data": [0.94, 500, 1500, "47 \/"], "isController": false}, {"data": [0.928, 500, 1500, "87 \/responsible_persons\/3\/notifications\/new-0"], "isController": false}, {"data": [0.9366666666666666, 500, 1500, "trigger_question\/select_ph_range-0"], "isController": false}, {"data": [0.41, 500, 1500, "64 \/sign-in"], "isController": false}, {"data": [0.9656666666666667, 500, 1500, "87 \/responsible_persons\/3\/notifications\/new-1"], "isController": false}, {"data": [0.8956666666666667, 500, 1500, "trigger_question\/select_ph_range-1"], "isController": false}, {"data": [0.9496666666666667, 500, 1500, "87 \/responsible_persons\/3\/notifications\/new-2"], "isController": false}, {"data": [0.6903333333333334, 500, 1500, "contains_cmrs"], "isController": false}, {"data": [0.5, 500, 1500, "69 \/two-factor\/sms"], "isController": false}, {"data": [0.9603333333333334, 500, 1500, "Upload product image-1"], "isController": false}, {"data": [0.9483333333333334, 500, 1500, "contains_nanomaterials-0"], "isController": false}, {"data": [0.7486666666666667, 500, 1500, "Upload product image-0"], "isController": false}, {"data": [0.9413333333333334, 500, 1500, "select_formulation_type-1"], "isController": false}, {"data": [0.9483333333333334, 500, 1500, "select_formulation_type-0"], "isController": false}, {"data": [0.681, 500, 1500, "select_formulation_type"], "isController": false}, {"data": [0.7226666666666667, 500, 1500, "Children under three?"], "isController": false}, {"data": [0.9413333333333334, 500, 1500, "select_special_applicator_type-0"], "isController": false}, {"data": [0.9453333333333334, 500, 1500, "select_special_applicator_type-1"], "isController": false}, {"data": [0.9463333333333334, 500, 1500, "contains_nanomaterials-1"], "isController": false}, {"data": [0.9483333333333334, 500, 1500, "select_category?category=tooth_whiteners-0"], "isController": false}, {"data": [0.95, 500, 1500, "select_category?category=tooth_whiteners-1"], "isController": false}, {"data": [0.92, 500, 1500, "69 \/two-factor\/sms-0"], "isController": false}, {"data": [0.7043333333333334, 500, 1500, "select_category"], "isController": false}, {"data": [0.57, 500, 1500, "69 \/two-factor\/sms-2"], "isController": false}, {"data": [0.93, 500, 1500, "69 \/two-factor\/sms-1"], "isController": false}, {"data": [0.9506666666666667, 500, 1500, "Children under three?-0"], "isController": false}, {"data": [0.9503333333333334, 500, 1500, "Children under three?-1"], "isController": false}, {"data": [0.9596666666666667, 500, 1500, "select_category-0"], "isController": false}, {"data": [0.98, 500, 1500, "SignIn Page"], "isController": true}, {"data": [0.9483333333333334, 500, 1500, "select_category-1"], "isController": false}, {"data": [0.9516666666666667, 500, 1500, "Single or Multi_item-1"], "isController": false}, {"data": [0.946, 500, 1500, "add_physical_form-0"], "isController": false}, {"data": [0.59, 500, 1500, "Cosmetics Products page"], "isController": false}, {"data": [0.953, 500, 1500, "Single or Multi_item-0"], "isController": false}, {"data": [0.9533333333333334, 500, 1500, "add_physical_form-1"], "isController": false}, {"data": [0.9426666666666667, 500, 1500, "select_frame_formulation-0"], "isController": false}, {"data": [0.9406666666666667, 500, 1500, "select_frame_formulation-1"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 89050, 0, 0.0, 421.9239191465464, 29, 4404, 771.0, 938.0, 1341.9800000000032, 50.78895407264103, 604.4770093852269, 150.1285751352851], "isController": false}, "titles": ["Label", "#Samples", "KO", "Error %", "Average", "Min", "Max", "90th pct", "95th pct", "99th pct", "Transactions\/s", "Received", "Sent"], "items": [{"data": ["Enter internal ref number-0", 1500, 0, 0.0, 267.5373333333336, 36, 1201, 455.9000000000001, 520.8500000000001, 693.9200000000001, 0.8899130139692611, 1.9933460555050613, 1.7200935137280948], "isController": false}, {"data": ["Enter internal ref number-1", 1500, 0, 0.0, 293.07000000000016, 45, 1339, 489.0, 551.8500000000001, 783.8600000000001, 0.8899383569364762, 11.508399511683407, 1.5068516215047671], "isController": false}, {"data": ["contains_poisonous_ingredients-1", 1500, 0, 0.0, 296.9613333333332, 46, 1117, 494.0, 561.95, 721.96, 0.890471421507022, 12.127404495455924, 1.5434078521250507], "isController": false}, {"data": ["Create a new product", 1500, 0, 0.0, 12421.681333333348, 2452, 25352, 16976.800000000003, 17999.95, 22675.550000000007, 0.8880579297948763, 311.05536448228594, 77.60024390973572], "isController": true}, {"data": ["add_physical_form", 1500, 0, 0.0, 619.0353333333334, 93, 2116, 985.0, 1133.0, 1384.98, 0.8898375216008059, 13.681744317720046, 3.22827607216523], "isController": false}, {"data": ["64 \/sign-in-1", 50, 0, 0.0, 135.24, 29, 818, 451.0999999999999, 594.0999999999997, 818.0, 0.42246856833851565, 0.9175489218602135, 0.5672795717436122], "isController": false}, {"data": ["64 \/sign-in-2", 50, 0, 0.0, 172.02, 44, 845, 510.59999999999997, 727.3999999999996, 845.0, 0.4198223311894407, 5.339910462392315, 0.6584322889553141], "isController": false}, {"data": ["64 \/sign-in-0", 50, 0, 0.0, 723.0399999999998, 561, 1479, 1059.6, 1259.799999999999, 1479.0, 0.4231550440081246, 0.8454836133209208, 0.5758214497291808], "isController": false}, {"data": ["select_frame_formulation", 1500, 0, 0.0, 626.2553333333336, 107, 2343, 1023.9000000000001, 1150.95, 1422.95, 0.8903387560899171, 32.533066253964975, 3.2978657615482874], "isController": false}, {"data": ["contains_poisonous_ingredients-0", 1500, 0, 0.0, 313.9946666666667, 48, 1417, 515.0, 588.7000000000003, 750.96, 0.8904439337913381, 2.0258399501974704, 1.7349436272931156], "isController": false}, {"data": ["Upload product image", 1500, 0, 0.0, 1101.352666666665, 236, 3468, 1674.9, 1867.95, 2215.86, 0.8898533580989411, 15.562206246429463, 18.473924038446707], "isController": false}, {"data": ["60 \/sign-in", 50, 0, 0.0, 120.91999999999999, 66, 569, 275.99999999999983, 458.8999999999994, 569.0, 0.42801623037545583, 5.149745825236693, 0.39290552397746925], "isController": false}, {"data": ["contains_special_applicator-1", 1500, 0, 0.0, 306.7933333333333, 47, 1494, 496.9000000000001, 570.9000000000001, 757.9300000000001, 0.8898491349776558, 13.0406788228957, 1.5431982685093069], "isController": false}, {"data": ["contains_cmrs-1", 1500, 0, 0.0, 310.5459999999993, 47, 1621, 508.9000000000001, 576.95, 729.97, 0.8898644439830332, 11.800996416441729, 1.5241066363494793], "isController": false}, {"data": ["contains_special_applicator-0", 1500, 0, 0.0, 299.8019999999991, 43, 1516, 484.0, 552.8500000000001, 757.8700000000001, 0.889858109158301, 2.031459163002429, 1.7250658374499157], "isController": false}, {"data": ["contains_cmrs-0", 1500, 0, 0.0, 309.46266666666736, 45, 1404, 511.0, 580.0, 805.99, 0.8898649718891656, 2.017570689761036, 1.6878739796585767], "isController": false}, {"data": ["trigger_question\/select_ph_range", 1500, 0, 0.0, 698.839333333332, 120, 2240, 1090.0, 1238.7000000000003, 1445.9, 0.890457148827446, 19.496308252779116, 3.213186794757938], "isController": false}, {"data": ["Product shades info-1", 1500, 0, 0.0, 309.30400000000037, 49, 990, 507.8000000000002, 578.9000000000001, 796.98, 0.889918293635067, 12.726292201223282, 1.5224607451478689], "isController": false}, {"data": ["Product shades info-0", 1500, 0, 0.0, 329.281333333333, 52, 1671, 525.9000000000001, 603.9000000000001, 811.99, 0.8899821825567052, 2.009145206600464, 1.7106726468648599], "isController": false}, {"data": ["select_category?category=oral_hygiene_products", 1500, 0, 0.0, 582.5853333333331, 90, 2219, 947.9000000000001, 1068.95, 1309.94, 0.8898533580989411, 13.378733203647093, 3.3231029086488406], "isController": false}, {"data": ["select_category?category=oral_hygiene_products-0", 1500, 0, 0.0, 280.2326666666668, 40, 1158, 471.9000000000001, 527.95, 688.95, 0.889887144512333, 2.0489060561714565, 1.7547630143399382], "isController": false}, {"data": ["Single or Multi_item", 1500, 0, 0.0, 602.4046666666666, 90, 2202, 989.7000000000003, 1111.0, 1400.99, 0.8899573651091621, 14.672297164254685, 3.2017780587982965], "isController": false}, {"data": ["select_category?category=oral_hygiene_products-1", 1500, 0, 0.0, 302.1913333333333, 45, 1426, 495.8000000000002, 558.0, 741.0, 0.8901781305784495, 11.334040048223917, 1.568978943615227], "isController": false}, {"data": ["Product shades info", 1500, 0, 0.0, 638.7346666666675, 107, 2552, 1047.6000000000004, 1173.95, 1480.8300000000002, 0.889887144512333, 14.734777409747647, 3.2328974258234573], "isController": false}, {"data": ["87 \/responsible_persons\/3\/notifications\/new", 1500, 0, 0.0, 892.7553333333334, 164, 3051, 1499.0, 1644.8000000000002, 2117.98, 0.889363743248988, 15.314969304536824, 4.049603160339239], "isController": false}, {"data": ["landing page - unsigned", 50, 0, 0.0, 256.59999999999997, 68, 1460, 552.0999999999999, 864.2999999999979, 1460.0, 0.42422854039928387, 5.574271877996114, 0.2228856979832175], "isController": true}, {"data": ["Enter Product name-0", 1500, 0, 0.0, 331.9906666666663, 81, 1480, 536.0, 618.8000000000002, 952.5900000000004, 0.8897045172670887, 1.9894036284671044, 1.7038785658244922], "isController": false}, {"data": ["Enter Product name-1", 1500, 0, 0.0, 297.4000000000002, 50, 1387, 494.0, 550.9000000000001, 798.8000000000002, 0.8898918959324822, 12.060432567922483, 1.4998206728784087], "isController": false}, {"data": ["SigningIn 2fa", 50, 0, 0.0, 2413.2000000000003, 1030, 7270, 5906.799999999999, 6475.149999999999, 7270.0, 0.405294770886866, 23.937761985072992, 3.782080980934934], "isController": true}, {"data": ["contains_special_applicator", 1500, 0, 0.0, 606.7340000000011, 94, 2152, 995.9000000000001, 1117.8500000000001, 1387.95, 0.8898232692344198, 15.071679389038444, 3.268151708786945], "isController": false}, {"data": ["select_category?category=tooth_whiteners", 1500, 0, 0.0, 613.1340000000014, 97, 2271, 985.0, 1109.8000000000002, 1375.7600000000002, 0.8901871113962347, 13.908164041381237, 3.298280021263603], "isController": false}, {"data": ["contains_nanomaterials", 1500, 0, 0.0, 603.3959999999998, 90, 1771, 1019.0, 1131.9, 1371.95, 0.8898422724910601, 14.130383031308508, 3.2353344168151716], "isController": false}, {"data": ["select_special_applicator_type", 1500, 0, 0.0, 614.599333333333, 98, 2346, 1017.9000000000001, 1149.0, 1356.0, 0.8898280199724865, 14.04483401722796, 3.3203052006295235], "isController": false}, {"data": ["Enter Product name", 1500, 0, 0.0, 629.5433333333312, 134, 2252, 1027.8000000000002, 1157.0, 1562.96, 0.8896696893688624, 14.046746822692983, 3.2032580334207386], "isController": false}, {"data": ["contains_poisonous_ingredients", 1500, 0, 0.0, 611.1140000000001, 96, 2359, 1012.8000000000002, 1144.9, 1457.95, 0.8904164477726232, 14.152433220621452, 3.2782026424592114], "isController": false}, {"data": ["Enter internal ref number", 1500, 0, 0.0, 560.7673333333328, 85, 2159, 941.9000000000001, 1054.95, 1393.96, 0.8898823931429223, 13.500953272609243, 3.2267911905054296], "isController": false}, {"data": ["Upload product image-2", 1500, 0, 0.0, 302.68866666666645, 50, 1143, 497.9000000000001, 558.0, 713.94, 0.890010697928589, 11.572613256457176, 1.4669931606015403], "isController": false}, {"data": ["47 \/", 50, 0, 0.0, 256.59999999999997, 68, 1460, 552.0999999999999, 864.2999999999979, 1460.0, 0.42483771199401826, 5.582276261874214, 0.22320575102810727], "isController": false}, {"data": ["87 \/responsible_persons\/3\/notifications\/new-0", 1500, 0, 0.0, 331.79400000000015, 76, 1493, 547.0, 612.95, 858.98, 0.8894133133314162, 1.9557469163299248, 1.337559498044773], "isController": false}, {"data": ["trigger_question\/select_ph_range-0", 1500, 0, 0.0, 326.28866666666636, 50, 1484, 522.0, 602.9000000000001, 755.99, 0.8904952103230953, 1.9494296767769537, 1.7116170174154148], "isController": false}, {"data": ["64 \/sign-in", 50, 0, 0.0, 1031.1399999999999, 649, 3076, 2080.2, 2465.049999999997, 3076.0, 0.41751212872733956, 7.05151890912431, 1.783575933974632], "isController": false}, {"data": ["87 \/responsible_persons\/3\/notifications\/new-1", 1500, 0, 0.0, 268.2879999999997, 36, 1237, 467.0, 540.95, 693.97, 0.8897108499051866, 1.9789914893967226, 1.3509407774723434], "isController": false}, {"data": ["trigger_question\/select_ph_range-1", 1500, 0, 0.0, 372.39666666666704, 66, 1266, 578.0, 638.9000000000001, 802.98, 0.8905242872688867, 17.54828489938708, 1.50175615563782], "isController": false}, {"data": ["87 \/responsible_persons\/3\/notifications\/new-2", 1500, 0, 0.0, 292.3826666666667, 49, 1321, 501.9000000000001, 560.95, 752.8500000000001, 0.8897198212968082, 11.385668672082787, 1.362249670840738], "isController": false}, {"data": ["contains_cmrs", 1500, 0, 0.0, 620.150666666666, 95, 2426, 1011.9000000000001, 1136.7000000000003, 1478.7200000000003, 0.8898375216008059, 13.818147835826164, 3.2118824379293835], "isController": false}, {"data": ["69 \/two-factor\/sms", 50, 0, 0.0, 1382.06, 374, 4404, 3681.3999999999996, 4187.599999999999, 4404.0, 0.40779375423086023, 17.197976795006156, 2.0633408197470047], "isController": false}, {"data": ["Upload product image-1", 1500, 0, 0.0, 295.3273333333331, 40, 1074, 482.0, 552.8500000000001, 715.96, 0.8900297091916929, 2.007514159260136, 1.4557252913289747], "isController": false}, {"data": ["contains_nanomaterials-0", 1500, 0, 0.0, 299.7020000000005, 43, 1204, 503.0, 576.8500000000001, 751.99, 0.8898691951607727, 2.003745579945552, 1.7104137563618231], "isController": false}, {"data": ["Upload product image-0", 1500, 0, 0.0, 503.038666666666, 137, 1845, 731.0, 790.95, 993.94, 0.8899277971913879, 1.9846884677964796, 15.553054320265733], "isController": false}, {"data": ["select_formulation_type-1", 1500, 0, 0.0, 316.3780000000001, 58, 1299, 513.9000000000001, 571.9000000000001, 729.95, 0.8903202600684834, 29.166998952426923, 1.5344860962023685], "isController": false}, {"data": ["select_formulation_type-0", 1500, 0, 0.0, 312.5719999999999, 48, 1216, 505.9000000000001, 572.0, 740.9300000000001, 0.8902964805986592, 2.0203574544079173, 1.7155636428068197], "isController": false}, {"data": ["select_formulation_type", 1500, 0, 0.0, 629.1053333333331, 109, 1950, 1018.7000000000003, 1132.7500000000002, 1421.97, 0.8902637198541866, 31.185429798365654, 3.249889162166878], "isController": false}, {"data": ["Children under three?", 1500, 0, 0.0, 581.7713333333331, 87, 2275, 993.0, 1122.7500000000002, 1470.97, 0.8899209334914624, 14.269680545824107, 3.1981116396911857], "isController": false}, {"data": ["select_special_applicator_type-0", 1500, 0, 0.0, 311.04666666666714, 49, 1289, 512.0, 568.95, 727.95, 0.8898565254662106, 2.0019095301409235, 1.7893664879661737], "isController": false}, {"data": ["select_special_applicator_type-1", 1500, 0, 0.0, 303.39866666666694, 45, 1372, 509.9000000000001, 570.9000000000001, 756.96, 0.8898591649561596, 12.043410134049868, 1.5310496194739744], "isController": false}, {"data": ["contains_nanomaterials-1", 1500, 0, 0.0, 303.54666666666697, 47, 961, 508.9000000000001, 576.0, 721.94, 0.8898739463892474, 12.127129723265517, 1.5250266897297273], "isController": false}, {"data": ["select_category?category=tooth_whiteners-0", 1500, 0, 0.0, 309.0433333333329, 49, 1828, 502.9000000000001, 555.95, 701.9300000000001, 0.8902135266164943, 2.018430507929132, 1.7501661685551062], "isController": false}, {"data": ["select_category?category=tooth_whiteners-1", 1500, 0, 0.0, 303.9333333333328, 46, 1444, 500.9000000000001, 567.8500000000001, 707.98, 0.8902499821950003, 11.890633160624835, 1.548275126749341], "isController": false}, {"data": ["69 \/two-factor\/sms-0", 50, 0, 0.0, 191.48, 35, 1252, 676.8, 971.0499999999988, 1252.0, 0.41706983417303395, 0.913154851773381, 0.7894349954956458], "isController": false}, {"data": ["select_category", 1500, 0, 0.0, 589.4033333333316, 88, 1957, 975.9000000000001, 1106.95, 1419.98, 0.8898512465332878, 14.232039256343898, 3.252662370044641], "isController": false}, {"data": ["69 \/two-factor\/sms-2", 50, 0, 0.0, 1011.6999999999999, 289, 3040, 2327.8, 2688.499999999998, 3040.0, 0.40809330645358755, 15.43479823101754, 0.6515783518743725], "isController": false}, {"data": ["69 \/two-factor\/sms-1", 50, 0, 0.0, 178.1, 36, 948, 573.4, 842.6499999999994, 948.0, 0.4146349553852788, 0.8964537308853286, 0.6511064533784456], "isController": false}, {"data": ["Children under three?-0", 1500, 0, 0.0, 288.40266666666616, 42, 1253, 499.9000000000001, 569.8000000000002, 786.8300000000002, 0.8899494449385312, 1.9951658409666275, 1.6887364317936813], "isController": false}, {"data": ["Children under three?-1", 1500, 0, 0.0, 293.23666666666725, 44, 1303, 500.0, 568.0, 752.8800000000001, 0.8899700376753983, 12.275255912738437, 1.5095125977112938], "isController": false}, {"data": ["select_category-0", 1500, 0, 0.0, 282.7473333333331, 40, 1037, 475.9000000000001, 553.0, 715.6300000000003, 0.889876058062633, 2.0593087654126534, 1.7060320257865318], "isController": false}, {"data": ["SignIn Page", 50, 0, 0.0, 120.91999999999999, 66, 569, 275.99999999999983, 458.8999999999994, 569.0, 0.42821417560206915, 5.152127434932856, 0.3930872315097119], "isController": true}, {"data": ["select_category-1", 1500, 0, 0.0, 306.4959999999996, 45, 1291, 505.0, 583.95, 758.98, 0.8898771139030841, 12.173141763245525, 1.5467228727265123], "isController": false}, {"data": ["Single or Multi_item-1", 1500, 0, 0.0, 300.71666666666727, 45, 1356, 496.9000000000001, 579.7000000000003, 775.8200000000002, 0.8899937997098619, 12.691538717696934, 1.503468959426666], "isController": false}, {"data": ["add_physical_form-0", 1500, 0, 0.0, 313.99800000000045, 47, 1142, 508.8000000000002, 579.95, 763.95, 0.8898998150788184, 2.0263401168171487, 1.6965134661493975], "isController": false}, {"data": ["Cosmetics Products page", 50, 0, 0.0, 1038.04, 282, 3102, 2280.5, 2800.849999999998, 3102.0, 0.4048156874175188, 15.310880420988074, 0.601673128537077], "isController": false}, {"data": ["Single or Multi_item-0", 1500, 0, 0.0, 301.5293333333337, 44, 1218, 496.9000000000001, 567.0, 737.99, 0.8899848227921553, 1.9813391412506778, 1.6984230479443723], "isController": false}, {"data": ["add_physical_form-1", 1500, 0, 0.0, 304.8979999999997, 45, 1153, 492.9000000000001, 561.7500000000002, 794.9200000000001, 0.8898686672496295, 11.655954007434556, 1.531934981041348], "isController": false}, {"data": ["select_frame_formulation-0", 1500, 0, 0.0, 313.05733333333336, 47, 1295, 515.0, 588.95, 779.99, 0.8903683513227312, 2.0326239960354866, 1.7564851787429305], "isController": false}, {"data": ["select_frame_formulation-1", 1500, 0, 0.0, 313.0493333333334, 56, 1389, 527.0, 590.95, 729.9200000000001, 0.8903947773003943, 30.502428950577748, 1.5415359564041942], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Percentile 1
            case 8:
            // Percentile 2
            case 9:
            // Percentile 3
            case 10:
            // Throughput
            case 11:
            // Kbytes/s
            case 12:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": []}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 89050, 0, null, null, null, null, null, null, null, null, null, null], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
