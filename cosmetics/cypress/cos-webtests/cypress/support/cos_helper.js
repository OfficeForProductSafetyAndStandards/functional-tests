export class SubmitNotifications {

    create_product_task(nanomaterial,Multiitem)
    {
        cy.get('.app-task-list__task-name').contains('Create the product').click()
        cy.get('#notification_product_name').type('NYX Professional Makeup Setting Spray Matte')
        cy.click_continue()
        cy.verify_h1('Internal reference')
        cy.select_radio('No')
        cy.click_continue()
        //cy.verify_h1('Is the product intended to be used on children under 3 years old?')
        cy.select_radio('No')
        cy.click_continue()
        cy.verify_h1('Nanomaterials')
        if(nanomaterial=='Yes')
        {
            cy.select_radio('Yes')
            cy.get('#nanomaterials_count').type('1')
        }
        else{
            cy.select_radio('No')
        }
        cy.click_continue()
        cy.verify_h1('Multi-item kits')
        if(Multiitem=='Yes')
        {
            cy.select_radio('Yes')

        }
        else{
            cy.select_radio('No')
        }
        
        cy.click_continue()
        if(Multiitem=='Yes')
        {
            
            cy.verify_h1('Upload images of the item labels')
        }
        else{
            cy.verify_h1('Upload an image of the product label')
        }
        
        cy.get('#image_upload').selectFile('./cypress/e2e/NYX.jpeg')
        cy.contains('Save and continue').click()
        cy.verify_success_banner('The task has been completed')
        cy.contains('Go to the task list page').click()
    }

    accept_submit_notification()
    {
        cy.get('.app-task-list__task-name').contains('Accept and submit').click()
        cy.verify_h1('Accept and submit - review')
        cy.click_continue()
        cy.verify_h1('Accept and submit')
        cy.get('.govuk-body').eq(1).invoke('prop','innerText').then( text => {
            expect(text).to.equal('By submitting this cosmetic product notification, you confirm that the details you provide are correct to the best of your knowledge.')
        })
        cy.get('.govuk-button').contains('Accept and submit').click()
        cy.verify_confirmation_panel('Submission complete')
    }

    select_test_image()
    {
        cy.get('#image_upload').selectFile('./cypress/e2e/NYX.jpeg')
    }

    create_product_details()
    {
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
    }

    enter_item_details(item_name)
    {
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
    }

}

export const submitNotifications  = new SubmitNotifications()