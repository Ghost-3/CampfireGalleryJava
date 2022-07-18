let input = document.querySelector('#input');
var i = 0;

function init () {
document.addEventListener("keyup", function(event)
    {
        document.getElementById("emergencyImage").style.display = 'none';
        if (event.key == "ArrowLeft")
        {
            document.getElementById('prev').click();
        }
        else if (event.key == "ArrowRight")
        {
            document.getElementById('next').click();
        }
        else if (event.key == "Enter")
        {
            document.querySelector('#inputButton').click();
        }
    });

    var images = document.querySelectorAll("img");
    for(var i = 0; i < images.length; i++)
    {
       images[i].addEventListener("load", () => document.getElementById("emergencyImage").style.visible = 'hidden');
    }

    input.focus();
}

function scrollToTop (duration)
{
    // cancel if already on top
    if (document.scrollingElement.scrollTop === 0){input.focus(); return;}

    const cosParameter = document.scrollingElement.scrollTop / 2;
    let scrollCount = 0, oldTimestamp = null;

    function step (newTimestamp)
    {
        if (oldTimestamp !== null)
        {
            // if duration is 0 scrollCount will be Infinity
            scrollCount += Math.PI * (newTimestamp - oldTimestamp) / duration;
            if (scrollCount >= Math.PI) return document.scrollingElement.scrollTop = 0;
            document.scrollingElement.scrollTop = cosParameter + cosParameter * Math.cos(scrollCount);
        }
        oldTimestamp = newTimestamp;
        window.requestAnimationFrame(step);
    }

    window.requestAnimationFrame(step);
}

function addError(){
    if(++i === location.pathname === "/" ? 1 : 6){
        document.getElementById("emergencyImage").style.display = '';
        i = 0;
        return;
    }
    document.getElementById("emergencyImage").style.display = 'none';

}

init();