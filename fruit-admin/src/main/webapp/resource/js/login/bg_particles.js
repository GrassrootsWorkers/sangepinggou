/* default dom id (particles-js) */
//particlesJS();

/* config dom id */
//particlesJS('dom-id');

/* config dom id (optional) + config particles params */
particlesJS('binggouparticles', {
  particles: {
    color: '#d1d1d1',
    shape: 'circle', // "circle", "edge" or "triangle"
    opacity: 0.5,
    size: 6, //圆点大小
    size_random: true,
    nb: 150,
    line_linked: {
      enable_auto: true,
      distance: 75,//密度
      color: '#d1d1d1',
      opacity: 1,
      width: 1,
      condensed_mode: {
        enable: false,
        rotateX: 800,//半径
        rotateY: 800
      }
    },
    anim: {
      enable: true,
      speed: 3  //速度
    }
  },
  interactivity: {
    enable: false,//滑动跟随鼠标动效果
    mouse: {
      distance: 300
    },
    detect_on: 'window', // "canvas" or "window"
    mode: 'grab',
    line_linked: {
      opacity: .9
    },
    events: {
      onclick: {
        enable: false,//点击效果
        mode: 'push', // "push" or "remove"
        nb: 4
      }
    }
  },
  /* Retina Display Support */
  retina_detect: true
});