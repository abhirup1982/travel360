var counter = 1;
$(document).ready(function() {
    var availableTags = [
        "Sports",
        "Nature",
        "History",
        "Art",
        "Theatre",
        "Cinema",
        "Adventure",
        "Shopping",
        "Gambling"
    ];

    var availableSalaryRange = [
        "< 10000",
        "10000 - 20000",
        "20001 - 40000",
        "> 40000"
    ];

    var availableAgeRange = [
        "< 2",
        "2 - 8",
        "8 - 12",
        "> 12"
    ];

    if($('#edit').val() == "true") {
        $('#uname').attr("disabled", true);
        $('#viewpswd').attr("disabled", true);
        $('#password').attr("disabled", true);
    }

    $("#marriageDt").keyup(function(e){
        if (e.keyCode != 8){
            if ($(this).val().length == 2){
                $(this).val($(this).val() + "/");
            }else if ($(this).val().length == 5){
                $(this).val($(this).val() + "/");
            }
        }
    });

    $("#age").keyup(function(e){
        if (e.keyCode != 8){
            if ($(this).val().length == 2){
                $(this).val($(this).val() + "/");
            }else if ($(this).val().length == 5){
                $(this).val($(this).val() + "/");
            }
        }
    });

    $('ul.tabs li').click(function(){
        var tab_id = $(this).attr('data-tab');

        $('ul.tabs li').removeClass('current');
        $('.tab-content').removeClass('current');

        $(this).addClass('current');
        $("#"+tab_id).addClass('current');
    })


    var erroEle = $('.error-message'),
        focusInput = $('.questions').find('.active');

    $('.navigation a').click(function() {
        nextMaster('navi');

        var thisInput = $('#' + $(this).attr('data-ref'));
        thisInput.val($(this).html());
        $('.active').removeClass('active');
        thisInput.focus().addClass('active')
        thisInput.closest('li').show();
        thisInput.closest('li').animate({
            marginTop: '0px'
        }, 200);
        thisInput.closest('li').prevAll('li').hide();
        thisInput.closest('li').prevAll('li').animate({
            marginTop: '-150px'
        }, 200)
        //                     .AddClass('done');
        thisInput.closest('li').nextAll('li').hide();
        thisInput.closest('li').nextAll('li').animate({
            marginTop: '150px'
        }, 200)
        //                    .RemoveClass('done');
        errorMessage(erroEle, '', 'hidden', 0);

    });

    if (focusInput.val() != '') {
        $('#next-page').show();
        //$('#next-page').css('opacity', 1);
    }

    $(document).keypress(function(event) {
        if (event.which == 13) {
            nextMaster('keypress');
            event.preventDefault();
        }

        $('#next-page').click(function() {
            var focusInput = $('.questions').find('.active');
            nextMaster('nextpage');

        })

    });

    function nextMaster(type) {
        var focusInput = $('.questions').find('.active');
        if (focusInput.attr('name') == "more" || focusInput.val() != '') {
            if ((focusInput.attr('name') == 'name' || focusInput.attr('name') == 'username') && focusInput.val().length < 2) {
                errorMessage(erroEle, "isn't your " + focusInput.attr('name') + " bit small. ", 'visible', 1);
            } else if (focusInput.attr('name') == 'email' && !validateEmail(focusInput.val())) {
                errorMessage(erroEle, "It doesn't look like a " + focusInput.attr('name'), 'visible', 1);
            } else if (focusInput.attr('name') == 'phone' && !validatePhone(focusInput.val())) {
                errorMessage(erroEle, "It doesn't look like a " + focusInput.attr('name'), 'visible', 1);
            } else if (focusInput.attr('name') == 'married' && !validateYesNo(focusInput.val())) {
                errorMessage(erroEle, "It doesn't look like a valid input", 'visible', 1);
            } else if (focusInput.attr('name') == 'notification' && !validateYesNo(focusInput.val())) {
                errorMessage(erroEle, "It doesn't look like a valid input", 'visible', 1);
            } else if (focusInput.attr('name') == 'marriageDt' && !validateDate(focusInput.val())) {
                errorMessage(erroEle, "It doesn't look like a valid input", 'visible', 1);
            } else if (focusInput.attr('name') == 'gender' && !validateGender(focusInput.val())) {
                errorMessage(erroEle, "It doesn't look like a valid input", 'visible', 1);
            } else if (focusInput.attr('name') == 'pets' && !validateYesNo(focusInput.val())) {
                errorMessage(erroEle, "It doesn't look like a valid input", 'visible', 1);
            } else if (focusInput.attr('name') == 'username' && $('#edit').val() == "false") {
                focusInput.attr("style", "background: url('/resources/img/loader.gif') no-repeat right center;")
                $.ajax({
                    type: "POST",
                    url: "/available",
                    data: "username=" + focusInput.val(),
                    success: function (payload) {
                        focusInput.attr("style", "")
                        if(payload) {
                            if (type != 'navi') showLi(focusInput);
                            $('#next-page').hide();
                            //$('#next-page').css('opacity', 0);
                            errorMessage(erroEle, '', 'hidden', 0);
                        }else {
                            errorMessage(erroEle, "The username " + focusInput.val() + " is already in use", 'visible', 1);
                        }
                    }
                });
            } else {
                if (type != 'navi') showLi(focusInput);
                $('#next-page').hide();
                //$('#next-page').css('opacity', 0);
                errorMessage(erroEle, '', 'hidden', 0);
            }
        } else if (type == 'keypress') {
            errorMessage(erroEle, 'please enter your ' + focusInput.attr('name'), 'visible', 1);
        }

    }

    $("input[type='text']").keyup(function(event) {
        var focusInput = $(this);
        if (focusInput.val().length > 1) {
            if ((focusInput.attr('name') == 'email' && !validateEmail(focusInput.val())) ||
                (focusInput.attr('name') == 'phone' && !validatePhone(focusInput.val())) ||
                (focusInput.attr('name') == 'married' && !validateYesNo(focusInput.val())) ||
                (focusInput.attr('name') == 'notification' && !validateYesNo(focusInput.val()))
            ) {
                $('#next-page').hide();
                //$('#next-page').css('opacity', 0);
            } else {
                $('#next-page').show();
                //$('#next-page').css('opacity', 1);
            }

        } else {
            $('#next-page').hide();
            //$('#next-page').css('opacity', 0);
        }
    });

    $("#password").keyup(function(event) {
        var focusInput = $(this);
        $("#viewpswd").val(focusInput.val());
        if (focusInput.val().length > 1) {
            $('#next-page').show();
            //$('#next-page').css('opacity', 1);
        }
    });

    $('[name=more]').click(function() {
        nextMaster('keypress');
    });

    $('#signup').click(function() {
        $('.navigation').fadeOut(400).css({
            'display': 'none'
        });
        $('#sign-form').fadeOut(400).css({
            'display': 'none'
        });
        $(this).fadeOut(400).css({
            'display': 'none'
        });
        $('#wf').animate({
            opacity: 1,
            marginTop: '1em'
        }, 500).css({
            'display': 'block'
        });
    });

    $('#signup1').click(function() {
        $('.navigation').fadeOut(400).css({
            'display': 'none'
        });
        $('#sign-form').fadeOut(400).css({
            'display': 'none'
        });
        $(this).fadeOut(400).css({
            'display': 'none'
        });
        $('#wf').animate({
            opacity: 1,
            marginTop: '1em'
        }, 500).css({
            'display': 'block'
        });
    });

    $('#show-pwd').mousedown(function() {
        $(this).toggleClass('view').toggleClass('hide');
        $('#password').css('opacity', 0);
        $('#viewpswd').css('opacity', 1);
    }).mouseup(function() {
        $(this).toggleClass('view').toggleClass('hide');
        $('#password').css('opacity', 1);
        $('#viewpswd').css('opacity', 0);
    });

    $( "#interests" )
    // don't navigate away from the field on tab when selecting an item
        .on( "keydown", function( event ) {
            if ( event.keyCode === $.ui.keyCode.TAB &&
                $( this ).autocomplete( "instance" ).menu.active ) {
                event.preventDefault();
            }
        })
        .autocomplete({
            minLength: 0,
            source: function( request, response ) {
                // delegate back to autocomplete, but extract the last term
                response( $.ui.autocomplete.filter(
                    availableTags, extractLast( request.term ) ) );
            },
            focus: function() {
                // prevent value inserted on focus
                return false;
            },
            select: function( event, ui ) {
                var terms = split( this.value );
                // remove the current input
                terms.pop();
                // add the selected item
                terms.push( ui.item.value );
                // add placeholder to get the comma-and-space at the end
                terms.push( "" );
                this.value = terms.join( ", " );
                return false;
            }
        });

    $( "#salary" )
    // don't navigate away from the field on tab when selecting an item
        .on( "keydown", function( event ) {
            if ( event.keyCode === $.ui.keyCode.TAB &&
                $( this ).autocomplete( "instance" ).menu.active ) {
                event.preventDefault();
            }
        })
        .autocomplete({
            minLength: 0,
            source: function( request, response ) {
                // delegate back to autocomplete, but extract the last term
                response( $.ui.autocomplete.filter(
                    availableSalaryRange, extractLast( request.term ) ) );
            },
            focus: function() {
                // prevent value inserted on focus
                return false;
            },
            select: function( event, ui ) {
                var terms = split( this.value );
                // remove the current input
                terms.pop();
                // add the selected item
                terms.push( ui.item.value );
                this.value = terms;
                return false;
            }
        });

    $( "#kids" )
    // don't navigate away from the field on tab when selecting an item
        .on( "keydown", function( event ) {
            if ( event.keyCode === $.ui.keyCode.TAB &&
                $( this ).autocomplete( "instance" ).menu.active ) {
                event.preventDefault();
            }
        })
        .autocomplete({
            minLength: 0,
            source: function( request, response ) {
                // delegate back to autocomplete, but extract the last term
                response( $.ui.autocomplete.filter(
                    availableAgeRange, extractLast( request.term ) ) );
            },
            focus: function() {
                // prevent value inserted on focus
                return false;
            },
            select: function( event, ui ) {
                var terms = split( this.value );
                // remove the current input
                terms.pop();
                // add the selected item
                terms.push( ui.item.value );
                // add placeholder to get the comma-and-space at the end
                terms.push( "" );
                this.value = terms.join( ", " );
                return false;
            }
        });

});

function showLi(focusInput) {
    focusInput.closest('li').hide();
    focusInput.closest('li').animate({
        marginTop: '-150px'
    }, 200);

    console.log(focusInput.closest('li'));

    if (focusInput.attr('id') == 'viewpswd') {
        $("[data-ref='" + focusInput.attr('id') + "']")
            .addClass('done').html('password');
        //                    .html(Array(focusInput.val().length+1).join("*"));
        $("[hidden-ref='" + focusInput.attr('id') + "']").val(focusInput.val());
    } else {
        $("[hidden-ref='" + focusInput.attr('id') + "']").val(focusInput.val());
        $("[data-ref='" + focusInput.attr('id') + "']").addClass('done').html(focusInput.val());
    }

    focusInput.removeClass('active');
    counter++;

    var nextli;

    if(focusInput.attr('name') == 'married' && focusInput.val().toLowerCase() == 'no') {
        nextli = focusInput.closest('li').next('li').next('li');
        nextli.show();
        nextli.animate({
            marginTop: '0px'
        }, 200);
        counter++;
    } else {
        nextli = focusInput.closest('li').next('li');
        nextli.show();
        nextli.animate({
            marginTop: '0px'
        }, 200);
    }


    nextli.find('input').focus().addClass('active');
    nextli.find('p').find('a').focus().addClass('active');
}

function errorMessage(textmeg, appendString, visib, opaci) {
    textmeg.css({
        visibility: visib
    }).animate({
        opacity: opaci
    }).html(appendString)
}

function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

function validatePhone(phone) {
    var re = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
    return re.test(phone);
}

function validateYesNo(value) {
    return value.toLowerCase() == "yes" || value.toLowerCase() == "no";
}

function validateDate(value) {
    return moment(value, 'MM/DD/YYYY',true).isValid()
}

function validateGender(value) {
    return value.toLowerCase() == "male" || value.toLowerCase() == "female" || value.toLowerCase() == "others";
}

function openCity(evt, cityName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
}

function split( val ) {
    return val.split( /,\s*/ );
}
function extractLast( term ) {
    return split( term ).pop();
}