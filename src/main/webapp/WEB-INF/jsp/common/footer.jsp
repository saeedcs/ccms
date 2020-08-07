<!-- footer start -->
<footer>
    <div class="copy-rights">
        &copy; Copyrights 2020 <a href="#">Kotlin Experts</a>
    </div>
    <div class="footer-navigation">
        <a href="#">Privacy Policy</a>
    </div>
</footer>
</div>

<!-- javascript files -->
<script type="text/javascript" src="/js/jquery-3.5.1.min.js?v=1"></script>
<script type="text/javascript" src="https://unpkg.com/aos@3.0.0-beta.6/dist/aos.js?v=1"></script>
<script type="text/javascript" src="/js/custom.js?v=1"></script>
<script type="text/javascript" src="/js/constant.js?v=1"></script>
<script type="text/javascript" src="/js/ccms.js?v=1"></script>
<div id="fb-root"></div>
<script async defer crossorigin="anonymous" src="https://connect.facebook.net/ur_PK/sdk.js#xfbml=1&version=v7.0" nonce="LFDCqdjE"></script>

<div id="subscribeModal" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
        <span class="close" id="sub-close">&times;</span>
        <p>Please enter your email address
        </p>
        <input name="subscribeEmail" id="subscribeEmail" type="text" class="form-control" placeholder="Email Address"
               />
        <p></p><buton id="subscribeMe" type="button" class="btn btn-info" onclick="home.subscribeMe(${page.id})">Subscribe</buton>


    </div>

</div>
<script>
    $(document).ready(function () {
        document.getElementById('subscribeEmail').addEventListener('keypress', e => home.onKeyPressed(e.key));

        document.getElementById('search-str').addEventListener('keypress', e => home.onSearchKeyPressed(e.key));
    });
</script>
<script>
    // Get the modal
    var modal1 = document.getElementById("subscribeModal");

    // Get the button that opens the modal
    var btn1 = document.getElementById("subscribe");

    // Get the <span> element that closes the modal
    var span1 = document.getElementById("sub-close");

    // When the user clicks on the button, open the modal
    btn1.onclick = function() {
        modal1.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    span1.onclick = function() {
        closeModal();
    }
    function closeModal() {
        console.log("close ");
        modal1.style.display = "none";
    }


    var home = {
        onKeyPressed: function(key) {
            if(key === 'Enter') {
                home.subscribeMe();
            }
        },
        onSearchKeyPressed: function(key) {
            if(key === 'Enter') {
                home.searchButtonPressed();
            }
        },
        subscribeMe: function() {
            let data = {};
            data['email'] = document.getElementById('subscribeEmail').value;

            let params = $.extend({}, doAjax_params_default);
            params['url'] = appRoutes.APP + appRoutes.SUBSCRIBE;
            params['data'] = data;
            params['beforeSendCallbackFunction'] = home.beforeAjax;
            params['successCallbackFunction'] = home.doneSubscribing;
            params['requestType'] = appObjects.REQUEST_TYPE.post;
            //params['contentType'] = 'application/json';
            doAjax(params);
        },
        doneSubscribing: function (response) {
            console.log("User Subscribed" );
            console.log(response);
            closeModal();
        },
        beforeAjax: function() {
            let header = $("meta[name='_csrf_header']").attr("content");
            let token = $("meta[name='_csrf']").attr("content");
            $(document).ajaxSend(function (e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });
        },
        searchButtonPressed: function () {
            window.location.href = appRoutes.SEARCH + '/s?str=' + document.getElementById('search-str').value;
        }
    }
</script>
</body>
</html>