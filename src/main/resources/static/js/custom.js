// functions
function addBodyOverflowHidden(){
  jQuery('body').addClass('overflow-hidden');
}

function removeBodyOverflowHidden(){
  jQuery('body').removeClass('overflow-hidden');
}

function showOverlay() {
  jQuery('.overlay').removeClass('d-none');
}

function hideOverlay() {
  jQuery('.overlay').addClass('d-none');
}

function openLeftMenuTabView() {
  jQuery('.left-bar').addClass('open');
  jQuery('body').addClass('overflow-hidden');
  addBodyOverflowHidden();
  showOverlay();
}

function closeLeftMenuTabView() {
  jQuery('.left-bar').removeClass('open');
  jQuery('.left-bar').removeClass('mob-view');
  hideOverlay();
  addBodyOverflowHidden()
  
}

// ready function
jQuery(document).ready(() => {
  // init AOS
  AOS.init({
    // Global settings:
  disable: false, // accepts following values: 'phone', 'tablet', 'mobile', boolean, expression or function
  startEvent: 'DOMContentLoaded', // name of the event dispatched on the document, that AOS should initialize on
  initClassName: 'aos-init', // class applied after initialization
  animatedClassName: 'aos-animate', // class applied on animation
  useClassNames: false, // if true, will add content of `data-aos` as classes on scroll
  disableMutationObserver: false, // disables automatic mutations' detections (advanced)
  debounceDelay: 50, // the delay on debounce used while resizing window (advanced)
  throttleDelay: 99, // the delay on throttle used while scrolling the page (advanced)

  // Settings that can be overridden on per-element basis, by `data-aos-*` attributes:
  offset: 120, // offset (in px) from the original trigger point
  delay: 0, // values from 0 to 3000, with step 50ms
  duration: 1000, // values from 0 to 3000, with step 50ms
  easing: 'ease', // default easing for AOS animations
  once: false, // whether animation should happen only once - while scrolling down
  mirror: false, // whether elements should animate out while scrolling past them
  anchorPlacement: 'top-bottom', // defines which position of the element regarding to window should trigger the animation
  });

  jQuery('.leftmenu-toggle .open').on('click', function() {
    openLeftMenuTabView()
  })

  jQuery('.leftmenu-toggle .close, .overlay').on('click', function() {
    closeLeftMenuTabView()
  })

  jQuery('.leftbar-mobile-toggle').on('click', function(){
    jQuery('.left-bar').addClass('mob-view');
    jQuery('.overlay').removeClass('d-none');
    jQuery('body').addClass('overflow-hidden');
  })

  jQuery('.topnav-toggle').on('click', function() {
    jQuery('header .inner').addClass('open-nav');
    jQuery('body').addClass('overflow-hidden');
  })

  jQuery('.close-nav').on('click', function() {
    jQuery('header .inner').removeClass('open-nav');
    jQuery('body').removeClass('overflow-hidden');
  })

  jQuery('.selected-value').on('click', function() {
    const targetedElement = jQuery(this);
    targetedElement.siblings('ul').slideToggle('fast');
    targetedElement.toggleClass('focused')

  })

  jQuery( window ).scroll(function() {
    const verticalAdWrapper = jQuery('.google-add-vertical');
    const scrollTopValue = jQuery(window).scrollTop();
    if ( scrollTopValue > 200 ) {
      verticalAdWrapper.addClass('sticky');
    } else {
      verticalAdWrapper.removeClass('sticky');
    }
  });
})



