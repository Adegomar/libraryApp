export const oktaConfig = {
    clientId: '0oa8288em91SKCCNa5d7',
    issuer: 'https://dev-99556437.okta.com/oauth2/default',
    redirectUri: 'http://localhost:3000/login/callback',
    scopes: ['openid', 'profile', 'email'],
    pkce: true,
    disaleHttpsCheck: true,
    
}