const appObjects = {
    REQUEST_TYPE: {
        get: 'get',
        post: 'post'
    },
    ERROR_CLASS: {
        invalid: 'is-invalid',
        visible: 'visible',
        invisible: 'invisible',
        errClass: 'err'
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
    FILE_UPLOAD: '/upload/file-upload',
    RESET_PASSWORD: '/auth/reset',
    FORGET_PASSWORD: '/auth/forget',
    LOGIN: '/auth/login',
    USER_EXISTS: '/auth/user-exists',
    REGISTER: '/auth/register',
    PAGE_CREATE: '/create',
    PAGE_LIST: '/page',
    DELETE: '/delete',
    SEO_URI_CHECK: '/check-seo-uri'

}