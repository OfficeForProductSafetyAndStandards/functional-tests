/// <reference types="cypress" /> 

import { submitNotifications } from "../support/cos_helper"

describe('Cos regression tests', () => {
    beforeEach( () => {
        cy.visit('https://cosmetics:reviewAPP@cosmetics-pr-2869-submit-web.london.cloudapps.digital/')
        cy.SignInCos('nasirkhan.beis@gmail.com','testpassword')
        cy.select_radio('Nashtech1')
        cy.contains('Save and continue').click()
        cy.verify_h1('Responsible Person')
    })
    it('Single Item notification',() => {
        cy.contains('Cosmetic products').click()
        //cy.contains('Create a new product notification').click()
        cy.contains('Add a cosmetic product').click()
        //create a product with given param 
        submitNotifications.create_product_task('No','No')
        //enter product details
        submitNotifications.create_product_details()
        cy.verify_success_banner('The task has been completed')
        cy.contains('tasks list page').click()      
    })
    it('Single Item notification - e2e',() => {
        cy.contains('Cosmetic products').click()
        //cy.contains('Create a new product notification').click()
        cy.contains('Add a cosmetic product').click()
        //create a product with given param 
        submitNotifications.create_product_task('No','No')
        //enter product details
        submitNotifications.create_product_details()
        cy.verify_success_banner('The task has been completed')
        cy.contains('tasks list page').click()
        //Accept and submit
        submitNotifications.accept_submit_notification()
    })

    it('multi-iten notiification',() => {
        cy.contains('Cosmetic products').click()
        cy.contains('Create a new product notification').click()
        //cy.contains('Add a cosmetic product').click()
        //create a product with given param 
        submitNotifications.create_product_task('No','Yes')
        //enter multi-item task details
        cy.get('.app-task-list__task-name').contains('Define the multi-item kit').click()
        cy.verify_h1_fieldset('Does the kit contain items that need to be mixed?')
        cy.select_radio('No, the items are used in sequence')
        cy.click_continue()
        cy.verify_success_banner('The task has been completed')
        cy.contains('tasks list page').click()
// enter item details:
        cy.get('.app-task-list__task-name').contains('Item #1').click()
        submitNotifications.enter_item_details('Shampoo')
        //enter item 2 details
        cy.get('.app-task-list__task-name').contains('Item #2').click()
        submitNotifications.enter_item_details('Conditioner')
       
    })


    it('multi-iten notiification e2e',() => {
        cy.contains('Cosmetic products').click()
       cy.contains('Create a new product notification').click()
       //cy.contains('Add a cosmetic product').click()
       //create a product with given param 
       submitNotifications.create_product_task('No','Yes')
        //enter product details
        cy.get('.app-task-list__task-name').contains('Define the multi-item kit').click()
        cy.verify_h1_fieldset('Does the kit contain items that need to be mixed?')
        cy.select_radio('No, the items are used in sequence')
        cy.click_continue()
        cy.verify_success_banner('The task has been completed')
        cy.contains('tasks list page').click()
// enter item details:
        cy.get('.app-task-list__task-name').contains('Item #1').click()
        submitNotifications.enter_item_details('Shampoo')
        //enter item 2 details
        cy.get('.app-task-list__task-name').contains('Item #2').click()
        submitNotifications.enter_item_details('Conditioner')
        //Accept and submit
        submitNotifications.accept_submit_notification()
    })

    it('Delete a draft notification', ()=> {
        cy.contains('Cosmetic products').click()
        cy.contains('Draft notifications').click()
        cy.get('td').contains('Continue').click()
        cy.contains('Delete this draft').click()
        cy.contains('Yes').click()
        cy.get('.govuk-button-group').contains('Delete').click()
        cy.verify_success_banner('notification deleted')
    })
})