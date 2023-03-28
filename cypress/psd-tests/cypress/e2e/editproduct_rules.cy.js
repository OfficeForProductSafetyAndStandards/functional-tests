/// <reference types="cypress" /> 
import { psdHelper } from "../support/psd_helper"

describe('Edit product', () => {
    beforeEach(() => {
        cy.visit('https://psd:reviewAPP@psd-pr-2321.london.cloudapps.digital/')
        
      })

    it('Scenario:Unowned product cannot be edited -outside the case', () =>{
        cy.contains('Products').click()
        cy.contains('All products - Search').click()
        cy.contains('Fisher-Price Roaring Rainforest Baby Jumperoo').click()
        psdHelper.verify_link_not_visible('Edit this product')

    })
    it('Scenario: Product record owned by diff team is not editable', () => {
        psdHelper.login_as_opss_user()
        cy.contains('Products').click()
        cy.contains('All products - Search').click()
        cy.contains('Fisher-Price Roaring Rainforest Baby Jumperoo').click()
        cy.verify_summary_item('Southampton Council')
        psdHelper.verify_link_not_visible('Edit this product')
    })

    it.only('Scenario: Product record is editable if the same team owns the product record',() => {
        psdHelper.login_as_psd_user()
        cy.contains('Products').click()
        cy.contains('All products - Search').click()
        cy.contains('Fisher-Price Roaring Rainforest Baby Jumperoo').click()
        cy.verify_summary_item('Your team is the product record owner')
        psdHelper.verify_link_visible('Edit the product record')
    })



})