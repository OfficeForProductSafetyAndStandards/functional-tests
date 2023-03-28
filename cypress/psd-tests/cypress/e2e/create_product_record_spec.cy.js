/// <reference types="cypress" /> 
import { psdHelper } from "../support/psd_helper"

describe('Create a product record new flow', () => {
    beforeEach(() => {
        cy.visit('https://psd:reviewAPP@psd-pr-2321.london.cloudapps.digital/')
        
      })
 it('Scenario: create a product record as opss user',() => {
    psdHelper.login_as_opss_user()
    cy.contains('Products').click()
    cy.contains('Create a product record').click()
    cy.verify_h1('Create a product record')
    cy.get('#category').select('Construction products')
    cy.get('#subcategory').type('Exterior building material')
    cy.select_radio('No') // counter fiet
    cy.get('#has_markings-1').click()
    cy.get('#product_brand').type('BnQ')
    cy.get('#name').type('Exterior Clading building material')
    cy.get('#when_placed_on_market-1').click()
    cy.contains('Save').click()
    cy.verify_success_panel('Product record created')
    cy.contains("a", 'product record page').click()
    cy.verify_summary_item('No owner')
 })
 it.only('Scenario: create a product record as opss user',() => {
  psdHelper.login_as_psd_user()
  cy.contains('Products').click()
  cy.contains('Create a product record').click()
  cy.verify_h1('Create a product record')
  cy.get('#category').select('Construction products')
  cy.get('#subcategory').type('Exterior building material')
  cy.select_radio('No') // counter fiet
  cy.get('#has_markings-1').click()
  cy.get('#product_brand').type('BnQ')
  cy.get('#name').type('Exterior Clading building material')
  cy.get('#when_placed_on_market-1').click()
  cy.contains('Save').click()
  cy.verify_success_panel('Product record created')
  cy.contains("a", 'product record page').click()
  cy.verify_summary_item('No owner')
})

})