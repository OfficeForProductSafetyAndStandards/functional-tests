/// <reference types="cypress" /> 



import { psdHelper } from "../support/psd_helper"


describe('first test suite',() => {
    beforeEach(() => {
        cy.visit('https://psd:reviewAPP@psd-pr-2321.london.cloudapps.digital/')
       
      })

    it('Scenario: create allegation as OPSS user',() => {
       
       psdHelper.login_as_opss_user()
       cy.verify_h1('Your cases')
       psdHelper.create_opss_case_allegation('A business','Communication and media equipment','Damage to hearing')
    })

    it('Scenario: add a product to the opss case', () => {
        psdHelper.login_as_opss_user()
        cy.contains('Communication and media equipment').click()
        psdHelper.add_a_product('10','Spanish desk set')
        cy.verify_h1('Products')
        cy.get('.govuk-heading-m').contains('Spanish desk set')
    })
    it('Second scenario',() =>{
        psdHelper.login_as_opss_user() 

        cy.verify_page_header('Your cases')
        //cy.get('.govuk-heading-l').should('contain','Your cases')
        cy.contains('Team cases').click()
        cy.contains('All cases – Search').click()
        cy.contains('Pretty plastic').click()

    })

    it('Scenario: 4 - Create an enquiry',() => {
        psdHelper.login_as_opss_user()
        cy.get('.govuk-heading-l').should('contain','Your cases')
        cy.createcase('Enquiry')
    }) 


it('Scenario: As TS user, search for a product and create a case ',() =>{
    psdHelper.login_as_psd_user()
    psdHelper.search_product('Spanish desk set')
    cy.contains("a",'Spanish desk set').click()
    psdHelper.ts_create_a_case('08999') 
})
it('Scenario: add a product to the case',() =>{
psdHelper.login_as_psd_user()
cy.contains('All cases').click()
cy.contains('Auto test').click()
cy.contains('Products (').click()
cy.contains('Add a product to the case').click()
cy.get('#reference').type('10')
cy.contains('Continue').click()
cy.verify_h2('Spanish desk set')
cy.select_radio('Yes')
cy.contains('Save and continue').click()

})
it('Scenario: Verify TS new case creation flow ', () => {
    psdHelper.login_as_psd_user()
    cy.verify_h1('Product Safety Database')
    cy.contains('Create a case').click()
    cy.verify_inset_text('Creating a case starts from a product record page.')
    cy.contains('Go to the products search page').click()
    cy.verify_h1('All products – Search')
    cy.contains("a", 'Create a product record') 
})

it('Scenario: verify when a product is created, no owner assigned',() => {
    psdHelper.login_as_psd_user()
    psdHelper.create_a_product('Construction products','Exterior-cladding','Fibre Cement Exterior Wall Cladding Boards')
    cy.contains("a", 'product record page').click()
    cy.verify_summary_item('No owner')
})
it('Scenario: verify when a product is created, no owner assigned',() => {
    psdHelper.login_as_psd_user()
    psdHelper.create_a_product('Childcare articles and children’s equipment','Baby Jumperoo','Fisher-Price Roaring Rainforest Baby Jumperoo')
    cy.contains("a", 'product record page').click()
    cy.verify_summary_item('No owner')
})

it('Scenario: No owner', () => {
    psdHelper.login_as_psd_user()
    psdHelper.verify_link_visible('Products')
    cy.contains('Products').click()
    cy.contains('All products - Search').click()
    cy.contains('Fisher-Price Roaring Rainforest Baby Jumperoo').click()
    cy.verify_summary_item('No owner')
})

it('Scenario: Unowned product -team becomes the product owner when a case is created ', () => {
    psdHelper.login_as_psd_user()
    psdHelper.verify_link_visible('Products')
    cy.contains('Products').click()
    cy.contains('All products - Search').click()
    cy.contains('Fisher-Price Roaring Rainforest Baby Jumperoo').click()
    psdHelper.ts_create_a_case('88858')
    cy.contains('View the case').click()
    cy.contains('Products (').click()
    cy.verify_summary_item('Your team is the product record owner')
})

it('Scenario: add accidents/incidents', () => {
    
    psdHelper.login_as_psd_user()
    cy.contains('Your cases').click()
    cy.contains('Auto test').click()
    cy.get('a[href*="supporting-information"]').click()
    psdHelper.Add_supporting_info("accident or incidents")
    cy.select_radio('Accident')
    cy.click_continue()
    cy.verify_h1('Record an accident to the case')
    //cy.get('.govuk-body').contains('Spanish desk set')
    cy.get('#is_date_known').click()
    cy.get('.govuk-date-input__item').eq(0).type('2')
    cy.get('.govuk-date-input__item').eq(1).type('05')
    cy.get('.govuk-date-input__item').eq(2).type('2017')
    cy.select_radio('Normal use')
    cy.select_radio('Serious')
    cy.contains('Add accident').click()
    cy.verify_success_banner('The supporting information has been updated.')


})

it('Scenario: add an incident to the case', () => {
    
    psdHelper.login_as_psd_user()
    cy.contains('Your cases').click()
    cy.contains('Auto test').click()
    psdHelper.add_accident_incident('Incident')
})
it('Scenario: add corrective action', () => {
    
    psdHelper.login_as_psd_user()
    cy.contains('Your cases').click()
    cy.contains('Auto test').click()
    psdHelper.add_corrective_actions()
})

it('Scenario: No edit link for an unowned product- linked within a case', () => {

})



})