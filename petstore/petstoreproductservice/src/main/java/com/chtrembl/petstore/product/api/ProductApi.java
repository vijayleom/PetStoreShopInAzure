/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.9).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.chtrembl.petstore.product.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.chtrembl.petstore.product.model.DataPreload;
import com.chtrembl.petstore.product.model.ModelApiResponse;
import com.chtrembl.petstore.product.model.Product;
import com.postgres.jdbc.data.products.data.ProductsDetailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-12-21T10:17:09.908-05:00")

@Api(value = "product", description = "the product API")
public interface ProductApi {

	// wired in for the scenario the interface declarations need access to scoped
	// beans, all implementation should occur in Controller tho
	default Optional<NativeWebRequest> getRequest() {
		return Optional.empty();
	}

	// wired in for the scenario the interface declarations need access to scoped
	// beans, all implementation should occur in Controller tho
	public DataPreload getBeanToBeAutowired();
	
	// wired in for the scenario the interface declarations need access to scoped
	// beans, all implementation should occur in Controller tho
	public ProductsDetailService getDBBeanToBeAutowired();

	// wired in for the scenario the interface declarations need access to scoped
	// beans, all implementation should occur in Controller tho
	default List<Product> getPreloadedProducts() {
		return getBeanToBeAutowired().getProducts();
	}
	
	default List<Product> getDBLoadedProducts() {
		return getDBBeanToBeAutowired().getAllProducts();
	}

	
	@ApiOperation(value = "Add a new product to the store", nickname = "addProduct", notes = "", authorizations = {
			@Authorization(value = "petstore_auth", scopes = {
					@AuthorizationScope(scope = "write:products", description = "modify products in your account"),
					@AuthorizationScope(scope = "read:products", description = "read your products") }) }, tags = {
							"product", })
	@ApiResponses(value = { @ApiResponse(code = 405, message = "Invalid input") })
	@RequestMapping(value = "/product", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" }, method = RequestMethod.POST)
	ResponseEntity<Void> addProduct(
			@ApiParam(value = "Product object that needs to be added to the store", required = true) @Valid @RequestBody Product body);

	@ApiOperation(value = "Deletes a product", nickname = "deleteProduct", notes = "", authorizations = {
			@Authorization(value = "petstore_auth", scopes = {
					@AuthorizationScope(scope = "write:products", description = "modify products in your account"),
					@AuthorizationScope(scope = "read:products", description = "read your products") }) }, tags = {
							"product", })
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Product not found") })
	@RequestMapping(value = "/product/{productId}", produces = { "application/json",
			"application/xml" }, method = RequestMethod.DELETE)
	ResponseEntity<Void> deleteProduct(
			@ApiParam(value = "Product id to delete", required = true) @PathVariable("productId") Long productId,
			@ApiParam(value = "") @RequestHeader(value = "api_key", required = false) String apiKey);

	@ApiOperation(value = "Finds Products by status", nickname = "findProductsByStatus", notes = "Multiple status values can be provided with comma separated strings", response = Product.class, responseContainer = "List", authorizations = {
			@Authorization(value = "petstore_auth", scopes = {
					@AuthorizationScope(scope = "write:products", description = "modify products in your account"),
					@AuthorizationScope(scope = "read:products", description = "read your products") }) }, tags = {
							"product", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "successful operation", response = Product.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Invalid status value") })
	@RequestMapping(value = "/product/findByStatus", produces = { "application/json",
			"application/xml" }, method = RequestMethod.GET)
	ResponseEntity<List<Product>> findProductsByStatus(
			@NotNull @ApiParam(value = "Status values that need to be considered for filter", required = true, allowableValues = "available, pending, sold") @Valid @RequestParam(value = "status", required = true) List<String> status);

	@ApiOperation(value = "Finds Products by tags", nickname = "findProductsByTags", notes = "Multiple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing.", response = Product.class, responseContainer = "List", authorizations = {
			@Authorization(value = "petstore_auth", scopes = {
					@AuthorizationScope(scope = "write:products", description = "modify products in your account"),
					@AuthorizationScope(scope = "read:products", description = "read your products") }) }, tags = {
							"product", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "successful operation", response = Product.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Invalid tag value") })
	@RequestMapping(value = "/product/findByTags", produces = { "application/json",
			"application/xml" }, method = RequestMethod.GET)
	ResponseEntity<List<Product>> findProductsByTags(
			@NotNull @ApiParam(value = "Tags to filter by", required = true) @Valid @RequestParam(value = "tags", required = true) List<String> tags);

	@ApiOperation(value = "Find product by ID", nickname = "getProductById", notes = "Returns a single product", response = Product.class, authorizations = {
			@Authorization(value = "api_key") }, tags = { "product", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = Product.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Product not found") })
	@RequestMapping(value = "/product/{productId}", produces = { "application/json",
			"application/xml" }, method = RequestMethod.GET)
	ResponseEntity<Product> getProductById(
			@ApiParam(value = "ID of product to return", required = true) @PathVariable("productId") Long productId);

	@ApiOperation(value = "Update an existing product", nickname = "updateProduct", notes = "", authorizations = {
			@Authorization(value = "petstore_auth", scopes = {
					@AuthorizationScope(scope = "write:products", description = "modify products in your account"),
					@AuthorizationScope(scope = "read:products", description = "read your products") }) }, tags = {
							"product", })
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Product not found"),
			@ApiResponse(code = 405, message = "Validation exception") })
	@RequestMapping(value = "/product", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" }, method = RequestMethod.PUT)
	ResponseEntity<Void> updateProduct(
			@ApiParam(value = "Product object that needs to be added to the store", required = true) @Valid @RequestBody Product body);

	@ApiOperation(value = "Updates a product in the store with form data", nickname = "updateProductWithForm", notes = "", authorizations = {
			@Authorization(value = "petstore_auth", scopes = {
					@AuthorizationScope(scope = "write:products", description = "modify products in your account"),
					@AuthorizationScope(scope = "read:products", description = "read your products") }) }, tags = {
							"product", })
	@ApiResponses(value = { @ApiResponse(code = 405, message = "Invalid input") })
	@RequestMapping(value = "/product/{productId}", produces = { "application/json", "application/xml" }, consumes = {
			"application/x-www-form-urlencoded" }, method = RequestMethod.POST)
	ResponseEntity<Void> updateProductWithForm(
			@ApiParam(value = "ID of product that needs to be updated", required = true) @PathVariable("productId") Long productId,
			@ApiParam(value = "Updated name of the product") @RequestParam(value = "name", required = false) String name,
			@ApiParam(value = "Updated status of the product") @RequestParam(value = "status", required = false) String status);

	@ApiOperation(value = "uploads an image", nickname = "uploadFile", notes = "", response = ModelApiResponse.class, authorizations = {
			@Authorization(value = "petstore_auth", scopes = {
					@AuthorizationScope(scope = "write:products", description = "modify products in your account"),
					@AuthorizationScope(scope = "read:products", description = "read your products") }) }, tags = {
							"product", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "successful operation", response = ModelApiResponse.class) })
	@RequestMapping(value = "/product/{productId}/uploadImage", produces = { "application/json" }, consumes = {
			"multipart/form-data" }, method = RequestMethod.POST)
	ResponseEntity<ModelApiResponse> uploadFile(
			@ApiParam(value = "ID of product to update", required = true) @PathVariable("productId") Long productId,
			@ApiParam(value = "Additional data to pass to server") @RequestParam(value = "additionalMetadata", required = false) String additionalMetadata,
			@ApiParam(value = "file detail") @Valid @RequestPart("file") MultipartFile file);

}