package com.task.ui.components.fragment.registration

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.task.R
import com.task.databinding.RegistrationFragmentBinding
import com.task.ui.base.BaseFragment
import com.task.ui.components.activity.HomeActivity
import com.task.utils.DialogHelper
import com.task.utils.EMAIL_PATTERN
import com.task.utils.SingleEvent
import com.task.utils.showToast
import com.task.utils.toGone
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.regex.Matcher
import java.util.regex.Pattern

@AndroidEntryPoint
class RegistrationFragment : BaseFragment(), View.OnClickListener {

    companion object {
        fun newInstance() = RegistrationFragment()
    }

    private lateinit var viewModel: RegistrationViewModel
    private lateinit var binding: RegistrationFragmentBinding
    private var dialogHelper: DialogHelper? = null
    private lateinit var selectedGenderRadioButton: RadioButton
    private var cal: Calendar = Calendar.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RegistrationFragmentBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[RegistrationViewModel::class.java]
        init()
        observeViewModel()
        return binding.root
    }

    override fun init() {
        dialogHelper = activity?.let { DialogHelper(it) }
        initOnClickListener()
        (activity as HomeActivity).bottomNavigationVisible(isVisible = false)
    }

    override fun initOnClickListener() {
        binding.inlRegistrationHeader.imgCloseAppHeader.setOnClickListener(this)
        binding.btnJoinUs.setOnClickListener(this)
        binding.edtRegisterDob.setOnClickListener(this)
        onDobChangeListener()
    }

    override fun appHeaderAction() {
        binding.inlRegistrationHeader.imgLeftArrowAppHeader.toGone()
        binding.inlRegistrationHeader.tvTitleAppHeader.text =
            returnResString(R.string.registration)
        binding.inlRegistrationHeader.tvTitleAppHeader.textSize = 20.0F
        binding.inlRegistrationHeader.imgCloseAppHeader.toGone()
    }

    override fun observeViewModel() {
        observeToast(viewModel.showToast)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.inlRegistrationHeader.imgCloseAppHeader -> {
                navigateToDashboard()
            }

            binding.btnJoinUs -> {
                //registerJoinUs()
                val action =
                    RegistrationFragmentDirections.actionRegistrationFragmentToPaymentFragment()
                Navigation.findNavController(binding.root)
                    .navigate(action)
            }

            binding.edtRegisterDob -> {
                DatePickerDialog(
                    requireActivity(),
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }
    }



    private fun getGender(): String {
        return when (binding.rgRegisterGender.checkedRadioButtonId) {
            R.id.rbRegisterMale -> {
                "male"
            }

            else -> {
                "female"
            }
        }
    }

    private fun registrationValidation(): Boolean {
        return if (binding.edtRegisterName.text?.isEmpty() == true) {
            binding.edtRegisterName.error = returnResString(R.string.error_enter_name)
            false
        } else if (binding.edtRegisterDob.text?.isEmpty() == true) {
            binding.edtRegisterName.error = returnResString(R.string.error_dob)
            false
        } else if (binding.edtRegisterMobileNo.text?.isEmpty() == true) {
            binding.edtRegisterMobileNo.error = returnResString(R.string.error_enter_mobile_no)
            false
        } else if (binding.edtRegisterMobileNo.text?.length != 10) {
            binding.edtRegisterMobileNo.error = returnResString(R.string.error_invalid_mobile_no)
            false
        } else if (binding.edtRegisterEmailId.text?.isEmpty() == true) {
            binding.edtRegisterEmailId.error = returnResString(R.string.error_enter_email_id)
            false
        } else if (!emailValidator(binding.edtRegisterEmailId.text)) {
            binding.edtRegisterEmailId.error = returnResString(R.string.error_invalid_email_id)
            false
        } else {
            binding.edtRegisterName.error = null
            binding.edtRegisterDob.error = null
            binding.edtRegisterMobileNo.error = null
            binding.edtRegisterEmailId.error = null
            true
        }
    }

    private fun emailValidator(email: Editable?): Boolean {
        val pattern: Pattern = Pattern.compile(EMAIL_PATTERN)
        val matcher: Matcher = pattern.matcher(email)
        return matcher.matches()
    }

    private fun onDobChangeListener() {

    }

    private fun navigateToDashboard() {
        viewModel.storeRegisterSession(true)
        val action =
            RegistrationFragmentDirections.actionRegistrationFragmentToHomeFragment()
        Navigation.findNavController(binding.root)
            .navigate(action)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }

    private val dateSetListener =
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInView()
        }

    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.edtRegisterDob.setText(sdf.format(cal.time))
    }
}