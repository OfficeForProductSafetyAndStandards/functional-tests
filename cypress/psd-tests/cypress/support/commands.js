// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add('login', (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add('drag', { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add('dismiss', { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite('visit', (originalFn, url, options) => { ... })

//const { get } = require("cypress/types/lodash")

Cypress.Commands.add('signIn',(email,password) =>{
    cy.contains('Sign in to your account')
        .click()
        cy.get('#email').type(email)
        cy.get('#password').type(password)
        cy.contains('Continue').click()
        cy.get('#otp_code').type('11222')
        cy.contains('Continue').click()
        cy.contains('Accept analytics cookies').click()
        cy.contains('Hide cookie message').click()
})

Cypress.Commands.add('SignInCos',(email,password) => {
    cy.contains('Sign in').click()
    cy.get('#email').type(email)
        cy.get('#password').type(password)
        cy.contains('Continue').click()
        // cy.select_radio('Text message')
        // cy.click_continue()
        cy.get('#otp_code').type('11222')
        cy.contains('Continue').click()
        cy.contains('Accept analytics cookies').click()
        cy.contains('Hide cookie message').click()
})

Cypress.Commands.add('createcase',(type) => {
    cy.contains('Create a case').click()
    cy.contains(type).click()
    cy.contains('Continue').click()

})

Cypress.Commands.add('verify_h1',(h1)=>{
    cy.get('.govuk-heading-l').should('contain',h1)
})
Cypress.Commands.add('verify_h1_fieldset', (h1) => {
    cy.get('.govuk-fieldset__heading').should('contain',h1)
})
Cypress.Commands.add('verify_h1_label',(h1) =>{
    cy.get('.govuk-label-wrapper').should('contain',h1)

})
//verify success confirmation panel message
Cypress.Commands.add('verify_confirmation_panel', (message) => {
    cy.get('.govuk-panel__title').should('contain',message)
})
//verify success banner
Cypress.Commands.add('verify_success_banner',(message) =>{
    cy.get('.govuk-notification-banner__heading').invoke('prop','innerHTML').then (text => {
        expect(text).to.contains(message)
    })
})
//verify banner content 
Cypress.Commands.add('verify_notification_banner',(baner_text) => {
    cy.get('.govuk-notification-banner__content').invoke('prop','innertText').then (text => {
        expect(text).equal(baner_text)
    })
})

Cypress.Commands.add('verify_page_header',(h1) => {
    cy.get('.govuk-heading-l').invoke('prop','innerText').then( text => {
        expect(text).to.equal(h1)
    })
})

Cypress.Commands.add('select_radio', (option) => {
    cy.get('.govuk-radios__item').contains(option).click()
})

    Cypress.Commands.add('verify_success_panel',(h1) => {
        cy.get('.govuk-panel__title').invoke('prop','innerText').then( text => {
            expect(text).to.equal(h1)
        })
    })
Cypress.Commands.add('verify_h2', (h2) =>{
    cy.get('.govuk-heading-m').invoke('prop','innerText').then( text => {
        expect(text).to.equal(h2)
    })
})

Cypress.Commands.add('click_continue',() => {
    cy.contains('Continue').click()
})
Cypress.Commands.add('verify_inset_text',(text) => {
    cy.get('.govuk-inset-text').invoke('prop','innerText').then(text => {
        expect(text).to.equal(text)
})
})


Cypress.Commands.add('verify_summary_item', (link_text) => { 
    cy.get('.govuk-summary-list__row').eq(2).find('.govuk-summary-list__value').invoke('prop','innerText').then(text => {
        expect(text).to.equal(link_text)
    })
})

Cypress.Commands.add('verify_product_version_text',(version_text) => {
    cy.get('.govuk-summary-list__row').find('.govuk-summary-list__value').invoke('prop','innerText').then (text => {
        expect(text).contains(version_text)
    })
})


//Create the product task 
Cypress.Commands.add('create_product_task',(product_name) => {
    cy.get('.app-task-list__task-name').contains('Create the product').click()
        cy.get('#notification_product_name').type('Cypress auto test product')
        cy.click_continue()
        cy.verify_h1('Internal reference')
        cy.select_radio('No')
        cy.click_continue()
        //cy.verify_h1('Is the product intended to be used on children under 3 years old?')
        cy.select_radio('No')
        cy.click_continue()
        cy.verify_h1('Nanomaterials')
        cy.select_radio('No')
        cy.click_continue()
        cy.verify_h1('Multi-item kits')
        cy.select_radio('No')
        cy.click_continue()
        cy.verify_h1('Upload an image of the product label')
        cy.get('#image_upload').selectFile('./cypress/e2e/makeupset.jpeg')
        cy.contains('Save and continue').click()
        cy.contains('tasks list page').click()
})
    Cypress.Commands.add('create_a_case',(case_name) => {
    cy.contains('Products').click()
    cy.contains('All products - Search').click()
    cy.contains('Spanish desk set').click()
    cy.contains('Create a new case for this product').click()
    cy.contains('A product is of concern').click()
    cy.contains('Continue').click()
    cy.contains('The product is unsafe (or suspected of being)').click()
    cy.get('#hazard_type').select('Asphyxiation')
    cy.get('#hazard_description').type('Auto test - hazard description')
    cy.contains('Continue').click()
    cy.contains('Yes').click()
    cy.get('#complainant_reference').type('86868')
    cy.contains('Continue').click()
    const uuid = () => Cypress._.random(0, 1e6)
    const id = uuid()
    const testname = `Auto test${id}`  
    cy.get('#user_title').type(testname)
    cy.contains('Save').click()
    cy.verify_success_panel('Case created')
    })

//cosmetics service helper methods

Cypress.Commands.add('accept_submit_notification',() =>{
    cy.get('.app-task-list__task-name').contains('Accept and submit').click()
        cy.verify_h1('Accept and submit - review')
        cy.click_continue()
        cy.verify_h1('Accept and submit')
        cy.get('.govuk-body').eq(1).invoke('prop','innerText').then( text => {
            expect(text).to.equal('By submitting this cosmetic product notification, you confirm that the details you provide are correct to the best of your knowledge.')
        })
        cy.get('.govuk-button').contains('Accept and submit').click()
        cy.verify_confirmation_panel('Submission complete')
})

Cypress.Commands.add('select_test_image',() => {
    cy.get('#image_upload').selectFile('./cypress/e2e/makeupset.jpeg')
})

Cypress.Commands.add('enter_product_details',() => {
    cy.get('.app-task-list__task-name').contains('Product details').click()
    cy.verify_h1_fieldset('Is the product available in different shades?')
    cy.select_radio('No')
    cy.click_continue()
    cy.verify_h1_fieldset('What is the physical form of the product?')
    cy.select_radio('Solid or pressed powder')
    cy.click_continue()
    cy.verify_h1_fieldset('What is the product contained in?')
    cy.select_radio('A typical non-pressurised bottle, jar, sachet or other package')
    cy.click_continue()
    cy.verify_h1('Carcinogenic, mutagenic or reprotoxic substances')
    cy.select_radio('No')
    cy.click_continue()
    cy.verify_h1_fieldset('What category of cosmetic product is it?')
    cy.select_radio('Skin products')
    cy.click_continue()
    cy.verify_h1_fieldset('What category of skin products is the product?')
    cy.select_radio('Skin care products')
    cy.click_continue()
    cy.verify_h1_fieldset('What category of skin care products is the product?')
    cy.select_radio('Face care products other than face mask')
    cy.click_continue()
    cy.verify_h1_fieldset('How do you want to give the formulation of the product?')
    cy.select_radio('Choose a predefined frame formulation')
    cy.click_continue()
    cy.get('#component_frame_formulation').type('Skin')
    cy.click_continue()
    cy.click_continue()
    cy.verify_h1_fieldset('Does the product contain ingredients the National Poisons Information Service (NPIS) needs to know about?')
    cy.select_radio('No')
    cy.click_continue()
    cy.verify_h1_fieldset('What is the pH range of the product?')
    cy.select_radio('It does not have a pH')
    cy.click_continue()
})
//multi-item - enter item details 
Cypress.Commands.add('enter_item_details',(item_name) => {
        cy.get('.govuk-label').should('contain','What is the item name?')
        cy.get('#component_name').type(item_name)
        cy.contains('Save and continue').click()
        cy.verify_h1_fieldset('Is '+item_name+' available in different shades?')
        cy.select_radio('No')
        cy.click_continue() 
        cy.verify_h1_fieldset('What is the physical form of '+item_name+'?')
        cy.select_radio('Solid or pressed powder')
        cy.click_continue()
        cy.verify_h1_fieldset('What is '+item_name+' contained in?')
        cy.select_radio('A typical non-pressurised bottle, jar, sachet or other package')
        cy.click_continue()
        cy.verify_h1('Carcinogenic, mutagenic or reprotoxic substances')
        cy.select_radio('No')
        cy.click_continue()
        cy.verify_h1_fieldset('What category of cosmetic product is it?')
        cy.select_radio('Skin products')
        cy.click_continue()
        cy.verify_h1_fieldset('What category of skin products is '+item_name+'?')
        cy.select_radio('Skin care products')
        cy.click_continue()
        cy.verify_h1_fieldset('What category of skin care products is '+item_name+'?')
        cy.select_radio('Face care products other than face mask')
        cy.click_continue()
        cy.verify_h1_fieldset('How do you want to give the formulation of '+item_name+'?')
        cy.select_radio('Choose a predefined frame formulation')
        cy.click_continue()
        cy.get('#component_frame_formulation').type('Skin')
        cy.click_continue()
        cy.click_continue()
        cy.verify_h1_fieldset('Does the product contain ingredients the National Poisons Information Service (NPIS) needs to know about?')
        cy.select_radio('No')
        cy.click_continue()
        cy.verify_h1_fieldset('What is the pH range of the product?')
        cy.select_radio('It does not have a pH')
        cy.click_continue()
        cy.verify_success_banner('The task has been completed')
        cy.contains('tasks list page').click()
})