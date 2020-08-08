const appObjects = {
    REQUEST_TYPE: {
        get: 'get',
        post: 'post'
    },
    ERROR_CLASS: {
        invalid: 'is-invalid',
        visible: 'visible',
        invisible: 'invisible',
        errClass: 'Err'
    },
    ERROR_MSG: {
        email: 'Please enter your correct email',
        value: 2,
        password: 'Password length should at least be 6',
        confirmPassword: 'Your passwords do not match',
        userExists: 'This user id already exists',
        seoUriExists: 'This SEO URI exists'
    }
}

const appStrings = {
    RESET_SUCCESS: 'Your password has been reset successfully',
    RESET_FAIL: 'Your password reset request has been denied, Please contact your administrator',
    REGISTER_SUCCESS: 'You have been registered successfully',
    REGISTER_FAIL: 'You could not be registered',
    FILE_UPLOADED: 'Your file has been uploaded',
    LOGIN_SUCCESS: 'You have been loggedin successfully',
    LOGIN_FAIL: 'Login failed',

}

const appRoutes = {
    UPLOAD: '/upload',
    FILE_UPLOAD: '/file-upload',
    RESET_PASSWORD: '/reset',
    FORGET_PASSWORD: '/forget',
    LOGIN: '/login',
    AUTH: '/auth',
    USER_EXISTS: '/user-exists',
    REGISTER: '/register',
    PAGE_CREATE: '/create',
    PAGE_LIST: '/page',
    ARTICLE_LIST: '/article',
    ADD_COMMENT: '/add-comment',
    APP: '/app',
    DELETE: '/delete',
    SEO_URI_CHECK: '/check-seo-uri',
    PAGE_DELETE: '/delete',
    SUBSCRIBE: '/subscribe',
    ARTICLE_CREATE: '/create',
    SEARCH: '/search',
    ASSIGN_ROLES_ENABLED: '/assign-roles-enabled',

}