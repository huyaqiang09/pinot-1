$(document).ready(function() {


    /* Assign background color value to each heat-map-cell */
    $(".heat-map-cell").each(function (i, cell) {
        calcHeatMapCellBackground(cell);
    });

    /* Transform UTC time into user selected or browser's timezone and display date */
    $(".contributors-table-date").each(function(i, cell){
        transformUTCToTZDate(cell);
    });

    /* Transform UTC time into user selected or browser's timezone and display time */
    $(".contributors-table-time").each(function(i, cell){
        transformUTCToTZTime(cell);
    });

    $(".funnel-tabs li").on("click", function(){
        if(!$(this).hasClass("uk-active")) {

            $(".details-cell").toggleClass("hidden")
            //$(".subheader").toggleClass("hidden")

            $('.contributors-table-time:not(.full-date)').attr('colspan', function(index, attr){
                return attr == 3 ? null : 3;
            });

        }
    })

})