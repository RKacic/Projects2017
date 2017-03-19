$(document).ready(function () {
    $('#weather-display').hide();


    $('#getWeather').on('click', function () {
        $('#errorMessages').empty();
        $('#weather-display').show();

        var haveValidationErrors = checkAndDisplayValidationErrors($('#input-form').find('input'));

        if (haveValidationErrors) {

            return false;
        }


        var zipcode = $('#zipcode-input').val();
        var unitType = $('#unit-select').val();
        var tempSymbol = '';
        var urlCurrentImg = 'http://openweathermap.org/img/w/';

        var urlCurrentWithUnit = 'http://api.openweathermap.org/data/2.5/weather?zip=' + zipcode + ',us&appid=af4d6eb222b1abd594b5c9412cae11ad&units=' + unitType;
        var urlFiveDayWithUnit = 'http://api.openweathermap.org/data/2.5/forecast/daily?zip=' + zipcode + ',us&appid=af4d6eb222b1abd594b5c9412cae11ad&units=' + unitType;

        if (unitType === 'imperial') {
            tempSymbol = ' F';
        } else {
            tempSymbol = ' C';
        };

        function getDate(timestamp) {
            var d = new Date(timestamp * 1000);
            var date = d.toUTCString();
            var formattedDate = date.substring(0, 7);
            return formattedDate;
        }
        if (zipcode.length === 5) {
            $.ajax({
                type: 'GET',
                url: urlCurrentWithUnit,
                success: function (currentWeather) {
                    $('#currentCity').append(currentWeather.name),
                        $('#daily-weather-icon').append('<li><img src="' + urlCurrentImg + currentWeather.weather[0].icon + '.png" ><H6>' + currentWeather.weather[0].description + '</H6></li>'),
                        // <img src="html5.gif" alt="HTML5 Icon" width="128" height="128">
                        $('#daily-weather-conditions').append('<li>Current Temp: ' + currentWeather.main.temp + tempSymbol + '</li>'),
                        $('#daily-weather-conditions').append('<li>Humidity: ' + currentWeather.main.humidity + '%</li>'),
                        $('#daily-weather-conditions').append('<li>WInd Speed: ' + currentWeather.wind.speed + ' mph</li>')
                },
                error: function () {
                    $('#errorMessages')
                        .append($('<li>')
                            .attr({
                                class: 'list-group-item list-group-item-danger'
                            })
                            .text('Error calling web service. Please try again later.'));
                }
            });

            $.ajax({
                type: 'GET',
                url: urlFiveDayWithUnit,
                success: function (extendedWeather) {
                    $('#day1').append('<li>' + getDate(extendedWeather.list[0].dt) + '</li>'),
                        $('#day1').append('<li><img src="' + urlCurrentImg + extendedWeather.list[0].weather[0].icon + '.png" >' + extendedWeather.list[0].weather[0].description + '</li>'),
                        $('#day1').append('<li>H:' + extendedWeather.list[0].temp.max + tempSymbol + ' L:' + extendedWeather.list[0].temp.min + tempSymbol + '</li>'),

                        $('#day2').append('<li>' + getDate(extendedWeather.list[1].dt) + '</li>'),
                        $('#day2').append('<li><img src="' + urlCurrentImg + extendedWeather.list[1].weather[0].icon + '.png" >' + extendedWeather.list[1].weather[0].description + '</li>'),
                        $('#day2').append('<li>H:' + extendedWeather.list[1].temp.max + tempSymbol + ' L:' + extendedWeather.list[1].temp.min + tempSymbol + '</li>'),

                        $('#day3').append('<li>' + getDate(extendedWeather.list[2].dt) + '</li>'),
                        $('#day3').append('<li><img src="' + urlCurrentImg + extendedWeather.list[2].weather[0].icon + '.png" >' + extendedWeather.list[2].weather[0].description + '</li>'),
                        $('#day3').append('<li>H:' + extendedWeather.list[2].temp.max + tempSymbol + ' L:' + extendedWeather.list[2].temp.min + tempSymbol + '</li>'),

                        $('#day4').append('<li>' + getDate(extendedWeather.list[3].dt) + '</li>'),
                        $('#day4').append('<li><img src="' + urlCurrentImg + extendedWeather.list[3].weather[0].icon + '.png" >' + extendedWeather.list[3].weather[0].description + '</li>'),
                        $('#day4').append('<li>H:' + extendedWeather.list[3].temp.max + tempSymbol + ' L:' + extendedWeather.list[3].temp.min + tempSymbol + '</li>'),

                        $('#day5').append('<li>' + getDate(extendedWeather.list[4].dt) + '</li>'),
                        $('#day5').append('<li><img src="' + urlCurrentImg + extendedWeather.list[4].weather[0].icon + '.png" >' + extendedWeather.list[4].weather[0].description + '</li>'),
                        $('#day5').append('<li>H:' + extendedWeather.list[4].temp.max + tempSymbol + ' L:' + extendedWeather.list[4].temp.min + tempSymbol + '</li>')
                },
                error: function () {
                    $('#errorMessages')
                        .append($('<li>')
                            .attr({
                                class: 'list-group-item list-group-item-danger'
                            })
                            .text('Error calling web service. Please try again later.'));
                }
            });
        } else {
            printError('Zipcode must be 5 digits long');
        }
    })

});

function printError(error) {
    // $('#errorMessages').append('<li>' + error + '</li>');
    $('#errorMessages').append($('<li>').attr({
        class: 'list-group-item list-group-item-danger'
    }).text(error));
    $('#weather-display').hide();

}

function checkAndDisplayValidationErrors(input) {

    var errorMessages = [];

    input.each(function () {
        if (!this.validity.valid) {
            var errorField = $('label[for=' + this.id + ']').text();
            errorMessages.push(errorField + ' ' + this.validationMessage);
        }
    });
    if (errorMessages.length > 0) {
        $.each(errorMessages, function (index, message) {
            $('#errorMessages').append($('<li>').attr({
                class: 'list-group-item list-group-item-danger'
            }).text(message));
        });
        $('#weather-display').hide();
        return true;
    } else {
        return false;
    }
}