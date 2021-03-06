/**
 * This layout accepts the following parameters:
 *
 * @param pageTitle the page title
 * @param extraStyles , a list of CSS files to be added in the header
 * @param scripts , a list of scripts to be imported
 * @param contents the main page contents
 * @param extraFooter, a section to be added before closing body
 */

// main layout
yieldUnescaped '''<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->'''

html {
    head {
        meta charset: 'utf-8'
        meta 'http-equiv': 'X-UA-Compatible', content: 'IE=edge'
        meta name: 'viewport', content: "width=device-width, initial-scale=1"
        title(pageTitle)
        link(href: "${baseUri}img/favicon.ico", type: "image/x-ico", rel: "icon")
        List<String> styles = []
        if(!noBaseStyles) {
            styles.addAll(['bootstrap.css', 'font-awesome.min.css'])
        }
        styles.addAll(['style.css'])
        if(extraStyles) {
            styles.addAll(extraStyles as List)
        }
        styles.each {
            link rel: 'stylesheet', type: 'text/css', href:
                    "${baseUri}css/$it"
        }
    }

    body {
        a(href: 'https://github.com/grails/grails-core', target:'_blank') {
            div(id: 'fork-me') {
                p 'Fork me  on Github'
            }
        }

        div(id: 'st-container', class: "st-container st-effect-9") {
            nav(class: "st-menu st-effect-9", id: "menu-12") {
                h2(class: "icon icon-lab", 'Socialize')
                ul {
                    menu['Socialize'].each {
                        def (text,url, style) = [it.name, it.link, it.style ]
                        li {
                            a(href: addBaseToUri(url, baseUri), class: 'icon') { yieldUnescaped "<span class='fa $style'></span> $text" }
                        }
                    }
                }
            }

            // 'content push wrapper'

            div(class: 'st-pusher') {
                div(class: 'st-content') {
                    div(class: 'st-content-inner') {
                        yieldUnescaped '''<!--[if lt IE 7]>
                        <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
                    <![endif]-->'''
                        if (mainContent) {
                            mainContent()
                        }
                    }
                }
            }
        }

        List<String> scripts = []
        if(!noBaseScripts) {
            scripts.addAll(['vendor/jquery-1.10.2.min.js', 'vendor/bootstrap.js', 'vendor/modernizr.min.js'])
        }
        scripts.addAll(['vendor/classie.js', 'vendor/sidebarEffects.js',  'plugins.js'])
        if(extraScripts) {
            scripts.addAll(extraScripts as List)
        }
        scripts.each {
            yieldUnescaped "<script src='${baseUri}js/$it' defer></script>"
        }

        if (extraFooter) {
            extraFooter()
        }

        script '''
              (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
              (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
              m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
              })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

              ga('create', 'UA-82213539-2', 'auto');
              ga('send', 'pageview');
        '''

        script '''
adroll_adv_id = "HBWJH4CQCJGS5DJRSB4Z4D";
adroll_pix_id = "IVEQYFOZXZAPZMDVQH7BFE";
/* OPTIONAL: provide email to improve user identification */
/* adroll_email = "username@example.com"; */
(function () {
    var _onload = function(){
        if (document.readyState && !/loaded|complete/.test(document.readyState)){setTimeout(_onload, 10);return}
        if (!window.__adroll_loaded){__adroll_loaded=true;setTimeout(_onload, 50);return}
        var scr = document.createElement("script");
        var host = (("https:" == document.location.protocol) ? "https://s.adroll.com" : "http://a.adroll.com");
        scr.setAttribute('async', 'true');
        scr.type = "text/javascript";
        scr.src = host + "/j/roundtrip.js";
        ((document.getElementsByTagName('head') || [null])[0] ||
            document.getElementsByTagName('script')[0].parentNode).appendChild(scr);
    };
    if (window.addEventListener) {window.addEventListener('load', _onload, false);}
    else {window.attachEvent('onload', _onload)}
}());            
        '''
    }
}
